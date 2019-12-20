package com.larapin.footballappkotlin.main.team

import com.larapin.footballappkotlin.model.team.Team

/**
 * Created by Avin on 18/10/2018.
 * class interface TeamView
 */
interface TeamView{
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}