package com.larapin.footballappkotlin.detail.player

import com.larapin.footballappkotlin.model.player.PlayerDetail

/**
 * Created by Avin on 18/10/2018.
 * class interface PlayerDetailView
 */
interface PlayerDetailView{
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<PlayerDetail>)
}