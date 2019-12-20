package com.larapin.footballappkotlin.detail.player

import com.google.gson.Gson
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.model.player.PlayerDetailResponse
import com.larapin.footballappkotlin.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Avin on 18/10/2018.
 * class PlayerDetailPresenter
 */
class PlayerDetailPresenter(private val view: PlayerDetailView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getPlayerDetail(playerId: String){
        view.showLoading()
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayerDetail(playerId)),
                        PlayerDetailResponse::class.java
                )
            }
            view.showPlayerDetail(data.await().players)
            view.hideLoading()
        }
    }
}