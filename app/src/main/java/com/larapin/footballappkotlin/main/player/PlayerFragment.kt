package com.larapin.footballappkotlin.main.player


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.detail.player.PlayerDetailActivity
import com.larapin.footballappkotlin.model.player.Player
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


class PlayerFragment : Fragment(), PlayerView {

    private lateinit var listPlayer: RecyclerView
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_player, container, false)

        listPlayer = view.find(R.id.list_player)
        val teamId = arguments?.getString("teamid")

        adapter = PlayerAdapter(ctx, players){
            startActivity<PlayerDetailActivity>(
                    "idplayer" to "${it.playerId}")
        }
        listPlayer.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)

        presenter.getPlayerList(teamId)

        return view
    }

    override fun showPlayerList(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
