package com.larapin.footballappkotlin.main.event

import com.google.gson.Gson
import com.larapin.footballappkotlin.util.CoroutineContextProvider
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.model.event.EventResponse
import com.larapin.footballappkotlin.model.event.EventSearchResponse
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by Avin on 04/09/2018.
 * class EventPresenter
 */
class EventPresenter(private val view: EventView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getEventList(league: String?, event: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getEvent(league, event)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().events)
            view.hideLoading()
        }
    }

    fun getEventSearch(name: String?) {
        view.showLoading()

        async(UI) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getEventBySearch(name)),
                        EventSearchResponse::class.java
                )
            }
            view.showEventList(data.await().event)
            view.hideLoading()
        }
    }
}
