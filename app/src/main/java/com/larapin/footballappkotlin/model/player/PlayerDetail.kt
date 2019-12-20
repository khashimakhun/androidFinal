package com.larapin.footballappkotlin.model.player

import com.google.gson.annotations.SerializedName

/**
 * Created by Avin on 18/10/2018.
 * data class PlayerDetail
 */
data class PlayerDetail(
        @SerializedName("strPlayer")
        val playerName: String? = null,

        @SerializedName("strFanart2")
        val playerImg: String? = null,

        @SerializedName("strWeight")
        val playerWeight: String? = null,

        @SerializedName("strHeight")
        val playerHeight: String? = null,

        @SerializedName("strPosition")
        val playerPos: String? = null,

        @SerializedName("strDescriptionEN")
        val playerDesc: String? = null
)