package com.larapin.footballappkotlin.main.player

import com.larapin.footballappkotlin.model.player.Player

/**
 * Created by Avin on 18/10/2018.
 * class interface PlayerView
 */
interface PlayerView{
    fun showPlayerList(data: List<Player>)
}