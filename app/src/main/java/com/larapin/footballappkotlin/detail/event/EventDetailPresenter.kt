package com.larapin.footballappkotlin.detail.event

import com.google.gson.Gson
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.model.event.EventDetailResponse
import com.larapin.footballappkotlin.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Avin on 06/09/2018.
 * class EventDetailPresenter
 */

class EventDetailPresenter(private val view: EventDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getEventDetail(eventId: String?){
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getEventDetail(eventId)),
                        EventDetailResponse::class.java
                )
            }
            view.showEventDetail(data.await().events)
            view.hideLoading()
        }
    }
}