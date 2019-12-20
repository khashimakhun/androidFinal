package com.larapin.footballappkotlin.detail

import com.google.gson.Gson
import com.larapin.footballappkotlin.TestContextProvider
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.detail.player.PlayerDetailPresenter
import com.larapin.footballappkotlin.detail.player.PlayerDetailView
import com.larapin.footballappkotlin.model.player.PlayerDetail
import com.larapin.footballappkotlin.model.player.PlayerDetailResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Avin on 19/10/2018.
 * PlayerDetailPresenterTest
 */
class PlayerDetailPresenterTest{
    @Mock
    private
    lateinit var view: PlayerDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = PlayerDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPlayerDetail(){
        val playerDetail: MutableList<PlayerDetail> = mutableListOf()
        val response = PlayerDetailResponse(playerDetail)
        val playerId = "34145937"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayerDetail(playerId)),
                PlayerDetailResponse::class.java)).thenReturn(response)

        presenter.getPlayerDetail(playerId)

        Mockito.verify(view).showPlayerDetail(playerDetail)
    }
}