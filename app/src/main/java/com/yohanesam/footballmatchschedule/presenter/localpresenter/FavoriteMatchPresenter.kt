package com.yohanesam.footballmatchschedule.presenter.localpresenter

import android.content.Context
import android.util.Log
import android.view.View
import com.yohanesam.footballmatchschedule.helper.database
import com.yohanesam.footballmatchschedule.model.entites.Match
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.sql.SQLClientInfoException

class FavoriteMatchPresenter(
    val context: Context,
    val view: MatchView
) {

    fun getFavoriteMatch() {

        view.isLoad()

        val data: MutableList<Match> = mutableListOf()
        context.database.use {
            val favoriteLastMatch = select(Match.FAVORITE_MATCH).parseList(classParser<Match>())

            data.addAll(favoriteLastMatch)

            Log.d("DB", "BELOM KELAR DBNYA")
        }

        view.stopLoad()
        view.showResult(data)
        Log.d("DB", "UDAH KELAR DBNYA")
    }

    fun getSelectedFavoriteMatch(idEvent: String) {

        view.isLoad()

        val data: MutableList<Match> = mutableListOf()

        context.database.use {

            val favoriteDetailMatch = select(Match.FAVORITE_MATCH)
                .whereArgs(
                    Match.ID_EVENT + " = {id}",
                    "id" to idEvent
                )
                .parseList(classParser<Match>())

            Log.d("DB", "BELOM KELAR DBNYA")
            data.addAll(favoriteDetailMatch)
        }

        view.stopLoad()
        view.showResult(data)
        Log.d("DB", "UDAH KELAR DBNYA")
    }

    fun addToFavorite(view: View, context: Context, data: Match) {
        try {
            context.database.use {
                insert(
                    Match.FAVORITE_MATCH,
                    Match.ID_EVENT to data.id,
                    Match.ID_EVENT to data.idEvent,
                    Match.DATE to data.dateEvent,

                    // Home Team Column
                    Match.HOME_TEAM_ID to data.idHomeTeam,
                    Match.HOME_TEAM_NAME to data.strHomeTeam,
                    Match.HOME_TEAM_SCORE to data.intHomeScore,
                    Match.HOME_TEAM_SHOT to data.intHomeShots,
                    Match.HOME_TEAM_GOAL_DETAIL to data.strHomeGoalDetails,
                    Match.HOME_TEAM_YELLOW_CARDS to data.strHomeYellowCards,
                    Match.HOME_TEAM_RED_CARDS to data.strHomeRedCards,
                    Match.HOME_TEAM_LINEUP_SUBSTITUTE to data.strHomeLineupSubstitutes,

                    // AWAY Team Column
                    Match.AWAY_TEAM_ID to data.idAwayTeam,
                    Match.AWAY_TEAM_NAME to data.strAwayTeam,
                    Match.AWAY_TEAM_SCORE to data.intAwayScore,
                    Match.AWAY_TEAM_SHOT to data.intAwayShots,
                    Match.AWAY_TEAM_GOAL_DETAIL to data.strAwayGoalDetails,
                    Match.AWAY_TEAM_YELLOW_CARDS to data.strAwayYellowCards,
                    Match.AWAY_TEAM_RED_CARDS to data.strAwayRedCards,
                    Match.AWAY_TEAM_LINEUP_SUBSTITUTE to data.strAwayLineupSubstitutes
                )
            }
            snackbar(view, "Added To Favorite").show()
        } catch (e: SQLClientInfoException) {
            snackbar(view, "Error when adding your match. Please try again").show()
        }
    }

    fun removeFromFavorite(view: View, context: Context, data: Match) {
        try {
            context.database.use {
                delete(
                    Match.FAVORITE_MATCH,
                    Match.ID_EVENT + " = {id}",
                    "id" to data.idEvent.toString()
                )
            }

            snackbar(view, "Remove From Favorite").show()
        } catch (e: SQLClientInfoException) {
            snackbar(view, "Error when removing your match. Please try again").show()
        }
    }

    fun isFavorite(context: Context, data: Match): Boolean {

        var boolFavorite = false

        context.database.use {

            val favorites = select(Match.FAVORITE_MATCH)
                .whereArgs(
                    Match.ID_EVENT + " = {id} ",
                    "id" to data.idEvent.toString()
                )
                .parseList(classParser<Match>())

            boolFavorite = !favorites.isEmpty()

            Log.d("FavoriteIsEmpty", boolFavorite.toString())

        }

        return boolFavorite
    }

}