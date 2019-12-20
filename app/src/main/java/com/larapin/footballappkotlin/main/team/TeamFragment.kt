package com.larapin.footballappkotlin.main.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson

import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.detail.team.TeamDetailActivity
import com.larapin.footballappkotlin.model.team.Team
import com.larapin.footballappkotlin.util.invisible
import com.larapin.footballappkotlin.util.visible
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import java.net.URLEncoder


class TeamFragment : Fragment(), TeamView {

    private lateinit var listTeam: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_team, container, false)
        listTeam = view.find(R.id.list_team)
        progressBar = view.find(R.id.progress_bar_team)
        swipeRefresh = view.find(R.id.swipe_refresh_team)
        spinner = view.find(R.id.team_spinner)
        toolbar = view.find(R.id.toolbar_team)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        val spinnerItems = resources.getStringArray(R.array.league_name)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = TeamAdapter(ctx, teams){
            startActivity<TeamDetailActivity>(
                    "idteam" to "${it.teamId}"
            )
        }
        listTeam.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = URLEncoder.encode(spinner.selectedItem.toString(), "UTF-8")
                presenter.getTeamList(leagueName)
            }
        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }

        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search Team"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if(TextUtils.isEmpty(newText)){
                    presenter.getTeamList(leagueName)
                }else{
                    presenter.getTeambySearch(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

        })
    }

}