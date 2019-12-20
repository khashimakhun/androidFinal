package com.larapin.footballappkotlin.main

import com.google.gson.Gson
import com.larapin.footballappkotlin.TestContextProvider
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.main.team.TeamPresenter
import com.larapin.footballappkotlin.main.team.TeamView
import com.larapin.footballappkotlin.model.team.Team
import com.larapin.footballappkotlin.model.team.TeamResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Avin on 19/10/2018.
 * TeamPresenterTest
 */
class TeamPresenterTest {
    @Mock
    private
    lateinit var view: TeamView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList(){
        val team: MutableList<Team> = mutableListOf()
        val response = TeamResponse(team)
        val league = "English Premiere League"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeam(league)),
                TeamResponse::class.java)).thenReturn(response)

        presenter.getTeamList(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamList(team)
        Mockito.verify(view).hideLoading()
    }
}