package com.larapin.footballappkotlin.main

import com.google.gson.Gson
import com.larapin.footballappkotlin.TestContextProvider
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.main.event.EventPresenter
import com.larapin.footballappkotlin.main.event.EventView
import com.larapin.footballappkotlin.model.event.Event
import com.larapin.footballappkotlin.model.event.EventResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Avin on 19/10/2018.
 * EventPresenterTest
 */
class EventPresenterTest{
    @Mock
    private
    lateinit var view: EventView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: EventPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetEventList(){
        val event: MutableList<Event> = mutableListOf()
        val response = EventResponse(event)
        val leagueId = "4328"
        val eventType = "eventsnextleague"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getEvent(leagueId, eventType)),
                EventResponse::class.java)).thenReturn(response)

        presenter.getEventList(leagueId, eventType)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(event)
        Mockito.verify(view).hideLoading()
    }
}