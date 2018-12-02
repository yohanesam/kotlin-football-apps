package com.yohanesam.footballmatchschedule.model.responsesdata

import com.google.gson.annotations.SerializedName
import com.yohanesam.footballmatchschedule.model.entites.Player

class PlayerJSONArray {

    @SerializedName("players")
    val jsonArrayPlayer: List<Player>? = null

    @SerializedName("player")
    val jsonArrayPlayersFromTeam: List<Player>? = null

}