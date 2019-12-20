package com.larapin.footballappkotlin.model.player

import com.google.gson.annotations.SerializedName

/**
 * Created by Avin on 18/10/2018.
 * data class Player
 */
data class Player(
        @SerializedName("idPlayer")
        var playerId: String? = null,

        @SerializedName("strPlayer")
        var playerName: String? = null,

        @SerializedName("strCutout")
        var playerPic: String? = null,

        @SerializedName("strPosition")
        var playerPos: String? = null
)