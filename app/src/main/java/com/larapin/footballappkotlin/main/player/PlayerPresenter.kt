package com.larapin.footballappkotlin.main.player

import com.google.gson.Gson
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.model.player.PlayerResponse
import com.larapin.footballappkotlin.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Avin on 18/10/2018.
 * class PlayerPresenter
 */
class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getPlayerList(team: String?){
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayer(team)),
                        PlayerResponse::class.java
                )
            }
            view.showPlayerList(data.await().player)
        }
    }
}