package com.yohanesam.footballmatchschedule.model.entites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(

    // configuration
    var id: Long? = null,

    // Team Item
    val idTeam: String? = null,
    val strTeam: String? = null,
    val strTeamBadge: String? = null,
    var intFormedYear: String? = null,
    var strStadium: String? = null,
    var strDescriptionEN: String? = null

)  : Parcelable {

    companion object {

        const val FAVORITE_TEAM = "FAVORITE_TEAM"
        const val ID = "ID"

        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_FORMED_YEAR = "TEAM_FORMED_YEAR"
        const val TEAM_STADUIM = "TEAM_STADIUM"
        const val TEAM_DESCRIPTION = "TEAM_DESCRIPTION"

    }

}