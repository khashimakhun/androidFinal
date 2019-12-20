package com.larapin.footballappkotlin.model.team

import com.google.gson.annotations.SerializedName

/**
 * Created by Avin on 18/10/2018.
 * data class Team
 */
data class Team(
        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamLogo: String? = null
)