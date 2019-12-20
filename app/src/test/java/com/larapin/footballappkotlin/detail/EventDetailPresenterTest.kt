package com.larapin.footballappkotlin.detail

import com.google.gson.Gson
import com.larapin.footballappkotlin.TestContextProvider
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.detail.event.EventDetailPresenter
import com.larapin.footballappkotlin.detail.event.EventDetailView
import com.larapin.footballappkotlin.model.event.EventDetail
import com.larapin.footballappkotlin.model.event.EventDetailResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Avin on 19/10/2018.
 * EventDetailPresenterTest
 */

class EventDetailPresenterTest{
    @Mock
    private
    lateinit var view: EventDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: EventDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = EventDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetEventDetail(){
        val eventDetail: MutableList<EventDetail> = mutableListOf()
        val response = EventDetailResponse(eventDetail)
        val eventId = "441613"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getEventDetail(eventId)),
                EventDetailResponse::class.java)).thenReturn(response)

        presenter.getEventDetail(eventId)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventDetail(eventDetail)
        Mockito.verify(view).hideLoading()
    }
}