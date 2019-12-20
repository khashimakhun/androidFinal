package com.larapin.footballappkotlin.detail

import com.google.gson.Gson
import com.larapin.footballappkotlin.TestContextProvider
import com.larapin.footballappkotlin.api.ApiRepository
import com.larapin.footballappkotlin.api.TheSportDBApi
import com.larapin.footballappkotlin.detail.team.TeamDetailPresenter
import com.larapin.footballappkotlin.detail.team.TeamDetailView
import com.larapin.footballappkotlin.model.team.TeamDetail
import com.larapin.footballappkotlin.model.team.TeamDetailResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Avin on 19/10/2018.
 * TeamDetailPresenterTest
 */
class TeamDetailPresenterTest{
    @Mock
    private
    lateinit var view: TeamDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamDetail(){
        val teamDetail: MutableList<TeamDetail> = mutableListOf()
        val response = TeamDetailResponse(teamDetail)
        val teamId = "133604"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                TeamDetailResponse::class.java)).thenReturn(response)

        presenter.getTeamDetail(teamId)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showTeamDetail(teamDetail)
        Mockito.verify(view).hideLoading()
    }
}