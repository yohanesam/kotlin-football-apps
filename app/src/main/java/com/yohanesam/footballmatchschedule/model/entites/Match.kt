package com.yohanesam.footballmatchschedule.model.entites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(

    // configuration
    var id: Long? = null,
    var idEvent: String? = null,
    var dateEvent: String? = null,

    // Home Team Item
    var idHomeTeam: String? = null,
    var strHomeTeam: String? = null,
    var intHomeScore: String? = null,
    var intHomeShots: String? = null,
    var strHomeGoalDetails: String? = null,
    var strHomeYellowCards: String? = null,
    var strHomeRedCards: String? = null,
    var strHomeLineupSubstitutes: String? = null,

    // Home Team Item
    var idAwayTeam: String? = null,
    var strAwayTeam: String? = null,
    var intAwayScore: String? = null,
    var intAwayShots: String? = null,
    var strAwayGoalDetails: String? = null,
    var strAwayYellowCards: String? = null,
    var strAwayRedCards: String? = null,
    var strAwayLineupSubstitutes: String? = null

) : Parcelable {

    companion object {

        const val FAVORITE_MATCH = "FAVORITE_MATCH"
        const val ID = "ID"
        const val ID_EVENT = "ID_EVENT"
        const val DATE = "DATE"

        const val HOME_TEAM_ID = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME = "HOME_TEAM_NAME"
        const val HOME_TEAM_SCORE = "HOME_TEAM_SCORE"
        const val HOME_TEAM_SHOT = "HOME_TEAM_SHOT"
        const val HOME_TEAM_GOAL_DETAIL = "HOME_TEAM_GOAL_DETAIL"
        const val HOME_TEAM_YELLOW_CARDS = "HOME_TEAM_YELLOW_CARDS"
        const val HOME_TEAM_RED_CARDS = "HOME_TEAM_RED_CARDS"
        const val HOME_TEAM_LINEUP_SUBSTITUTE = "HOME_TEAM_LINEUP_SUBSTITUTE"

        const val AWAY_TEAM_ID = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME = "AWAY_TEAM_NAME"
        const val AWAY_TEAM_SCORE = "AWAY_TEAM_SCORE"
        const val AWAY_TEAM_SHOT = "AWAY_TEAM_SHOT"
        const val AWAY_TEAM_GOAL_DETAIL = "AWAY_TEAM_GOAL_DETAIL"
        const val AWAY_TEAM_YELLOW_CARDS = "AWAY_TEAM_YELLOW_CARDS"
        const val AWAY_TEAM_RED_CARDS = "AWAY_TEAM_RED_CARDS"
        const val AWAY_TEAM_LINEUP_SUBSTITUTE = "AWAY_TEAM_LINEUP_SUBSTITUTE"

    }

}
