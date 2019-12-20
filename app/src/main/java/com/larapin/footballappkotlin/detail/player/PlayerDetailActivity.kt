package com.larapin.footballappkotlin.detail.player

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.MenuItem
import android.widget.ProgressBar
import com.google.gson.Gson
import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.model.player.PlayerDetail
import com.larapin.footballappkotlin.util.invisible
import com.larapin.footballappkotlin.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.onRefresh


class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {
    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var details: PlayerDetail
    private var idPlayer = ""
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        progressBar = find(R.id.progress_bar_detail_player)
        swipeRefresh = find(R.id.swipe_refresh_detail_player)

        val intent = intent
        idPlayer = intent.getStringExtra("idplayer")

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerDetailPresenter(this, request, gson)
        presenter.getPlayerDetail(idPlayer)
        swipeRefresh.onRefresh {
            presenter.getPlayerDetail(idPlayer)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerDetail(data: List<PlayerDetail>) {
        details = PlayerDetail(data[0].playerName,
                data[0].playerImg,
                data[0].playerWeight,
                data[0].playerHeight,
                data[0].playerPos,
                data[0].playerDesc)
        swipeRefresh.isRefreshing = false
        Picasso.with(ctx).load(data[0].playerImg).into(img_player_detail)
        tv_weight.text = data[0].playerWeight
        tv_height.text = data[0].playerHeight
        tv_pos_detail.text = data[0].playerPos
        tv_desc_detail.text = data[0].playerDesc
        supportActionBar?.title = data[0].playerName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
