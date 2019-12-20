package com.larapin.footballappkotlin.detail.team

import com.larapin.footballappkotlin.model.team.TeamDetail

/**
 * Created by Avin on 18/10/2018.
 * class interface TeamDetailView
 */
interface TeamDetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<TeamDetail>)
}