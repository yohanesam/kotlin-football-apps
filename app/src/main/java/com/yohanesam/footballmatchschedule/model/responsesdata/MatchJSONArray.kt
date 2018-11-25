package com.yohanesam.footballmatchschedule.model.responsesdata

import com.google.gson.annotations.SerializedName
import com.yohanesam.footballmatchschedule.model.entites.Match

data class MatchJSONArray(

    @SerializedName("events")
    val arrMatchesResult: List<Match>? = null,

    @SerializedName("event")
    val event: List<Match>? = null

)