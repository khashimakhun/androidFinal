package com.larapin.footballappkotlin.detail.team

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.google.gson.Gson
import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.R.id.add_to_favorite
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.db.FavoriteTeam
import com.larapin.footballappkotlin.db.database
import com.larapin.footballappkotlin.model.team.TeamDetail
import com.larapin.footballappkotlin.main.player.PlayerFragment
import com.larapin.footballappkotlin.util.invisible
import com.larapin.footballappkotlin.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var details: TeamDetail
    private var idTeam: String = ""
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var toolbar: Toolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var relativeLayout: RelativeLayout

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        viewPager = find(R.id.viewpager_team_detail)
        tabs = find(R.id.tabs_team_detail)
        toolbar = find(R.id.toolbar_team_detail)
        relativeLayout = find(R.id.layout_detail_team)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressBar = find(R.id.progress_bar_team_detail)


        val fragmentAdapter = TeamPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        tabs.setupWithViewPager(viewPager)

        val intent = intent
        idTeam = intent.getStringExtra("idteam")

        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(idTeam)
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to idTeam)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to details.teamId,
                        FavoriteTeam.TEAM_NAME to details.teamName,
                        FavoriteTeam.TEAM_BADGE to details.teamBadge)
            }
            snackbar(relativeLayout, "Added to favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(relativeLayout, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                        "id" to idTeam)
            }
            snackbar(relativeLayout, "Removed from favorite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(relativeLayout, e.localizedMessage).show()
        }
    }

    private fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<TeamDetail>) {
        details = TeamDetail(data[0].teamId,
                data[0].teamName,
                data[0].teamBadge,
                data[0].year,
                data[0].stadium,
                data[0].desc)

        Picasso.with(this).load(data[0].teamBadge)
                .placeholder(R.drawable.progress_animation).into(img_team_detail)
        tv_team_detail.text = data[0].teamName
        tv_team_year.text = data[0].year
        tv_team_stadium.text = data[0].stadium

        val args1 = Bundle()
        val args2 = Bundle()
        args1.putString("teamoverview", data[0].desc)
        args2.putString("teamid", data[0].teamId)
        val fragment1 = TeamOverviewFragment()
        val fragment2 = PlayerFragment()
        fragment1.arguments = args1
        fragment2.arguments = args2
        supportFragmentManager.beginTransaction().replace(R.id.content_team_overview, fragment1).commit()
        supportFragmentManager.beginTransaction().replace(R.id.content_player, fragment2).commit()

    }
}
