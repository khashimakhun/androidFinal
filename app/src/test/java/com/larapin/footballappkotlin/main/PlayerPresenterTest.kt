package com.larapin.footballappkotlin.main

import com.google.gson.Gson
import com.larapin.footballappkotlin.TestContextProvider
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.main.player.PlayerPresenter
import com.larapin.footballappkotlin.main.player.PlayerView
import com.larapin.footballappkotlin.model.player.Player
import com.larapin.footballappkotlin.model.player.PlayerResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Avin on 19/10/2018.
 * PlayerPresenterTest
 */
class PlayerPresenterTest {
    @Mock
    private
    lateinit var view: PlayerView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPlayerList(){
        val player: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(player)
        val teamId = "133604"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayer(teamId)),
                PlayerResponse::class.java)).thenReturn(response)

        presenter.getPlayerList(teamId)

        Mockito.verify(view).showPlayerList(player)
    }
}