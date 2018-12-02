package com.yohanesam.footballmatchschedule.presenter.localpresenter

import android.content.Context
import android.view.View
import com.yohanesam.footballmatchschedule.helper.database
import com.yohanesam.footballmatchschedule.model.entites.Team
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.sql.SQLClientInfoException

class FavoriteTeamPresenter(
    val context: Context,
    val view: TeamView
) {

    fun getFavoriteTeam(){

        view.isLoad()

        val data: MutableList<Team> = mutableListOf()

        context.database.use {
            val favoriteLastMatch = select(Team.FAVORITE_TEAM).parseList(classParser<Team>())

            data.addAll(favoriteLastMatch)

        }


        view.stopLoad()
        view.showTeamResult(data)
    }

    fun getSelectedFavoriteTeam(idTeam: String){

        view.isLoad()

        val data: MutableList<Team> = mutableListOf()

        context.database.use {

            val favoriteDetailTeam = select(Team.FAVORITE_TEAM)
                .whereArgs(

                    Team.TEAM_ID + " = {id} ",
                    "id" to idTeam

                ).parseList(classParser<Team>())

        }

        view.stopLoad()
        view.showTeamResult(data)

    }

    fun addToFavorite(view: View, context: Context, data: Team){

        try {
            context.database.use {

                insert(

                    Team.FAVORITE_TEAM,
                    Team.ID to data.id,
                    Team.TEAM_ID to data.idTeam,
                    Team.TEAM_NAME to data.strTeam,
                    Team.TEAM_BADGE to data.strTeamBadge,
                    Team.TEAM_FORMED_YEAR to data.intFormedYear,
                    Team.TEAM_STADUIM to data.strStadium,
                    Team.TEAM_DESCRIPTION to data.strDescriptionEN

                )

            }

            snackbar(view, "Added To Favorite").show()

        } catch (e: SQLClientInfoException) {

            snackbar(view, "Error when adding your match. Please try again").show()

        }

    }

    fun removeFromFavorite(view: View, context: Context, data: Team){

        try {

            context.database.use {

                delete(

                    Team.FAVORITE_TEAM,
                    Team.TEAM_ID + " = {id}",
                    "id" to data.idTeam.toString()

                )

            }

            snackbar(view, "Remove From Favorite").show()
        } catch (e: SQLClientInfoException) {

            snackbar(view, "Error when removing your match. Please try again").show()

        }

    }

    fun isFavorite(context: Context, data: Team): Boolean{

        var boolFavorite = false

        context.database.use {

            val favorite = select(Team.FAVORITE_TEAM)
                .whereArgs(

                    Team.TEAM_ID + " = {id} ",
                    "id" to data.idTeam.toString()

                ).parseList(classParser<Team>())

            boolFavorite = !favorite.isEmpty()
        }

        return boolFavorite

    }

}