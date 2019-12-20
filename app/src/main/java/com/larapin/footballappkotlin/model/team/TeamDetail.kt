package com.larapin.footballappkotlin.model.team

import com.google.gson.annotations.SerializedName

/**
 * Created by Avin on 18/10/2018.
 * data class TeamDetail
 */
data class TeamDetail(
        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("intFormedYear")
        var year: String? = null,

        @SerializedName("strStadium")
        var stadium: String? = null,

        @SerializedName("strStadiumDescription")
        var desc: String? = null
)