package com.yohanesam.footballmatchschedule.model.responsesdata

import com.google.gson.annotations.SerializedName
import com.yohanesam.footballmatchschedule.model.entites.Team

data class TeamJSONArray(

    @SerializedName("teams")
    val jsonArrayTeamResult: List<Team>? = null

)