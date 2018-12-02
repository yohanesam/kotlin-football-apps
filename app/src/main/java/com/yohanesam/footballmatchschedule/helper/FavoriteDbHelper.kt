package com.yohanesam.footballmatchschedule.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.yohanesam.footballmatchschedule.model.entites.Match
import com.yohanesam.footballmatchschedule.model.entites.Team
import org.jetbrains.anko.db.*

class FavoriteMatchDbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db") {


    companion object {
        private var instance: FavoriteMatchDbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteMatchDbHelper {
            if (instance == null) {
                instance = FavoriteMatchDbHelper(ctx.applicationContext)
            }

            return instance!!
        }
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Match.FAVORITE_MATCH, true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.ID_EVENT to TEXT,
            Match.DATE to TEXT,

            // Home Team Column
            Match.HOME_TEAM_ID to TEXT,
            Match.HOME_TEAM_NAME to TEXT,
            Match.HOME_TEAM_SCORE to TEXT,
            Match.HOME_TEAM_SHOT to TEXT,
            Match.HOME_TEAM_GOAL_DETAIL to TEXT,
            Match.HOME_TEAM_YELLOW_CARDS to TEXT,
            Match.HOME_TEAM_RED_CARDS to TEXT,
            Match.HOME_TEAM_LINEUP_SUBSTITUTE to TEXT,

            // Away Team Column
            Match.AWAY_TEAM_ID to TEXT,
            Match.AWAY_TEAM_NAME to TEXT,
            Match.AWAY_TEAM_SCORE to TEXT,
            Match.AWAY_TEAM_SHOT to TEXT,
            Match.AWAY_TEAM_GOAL_DETAIL to TEXT,
            Match.AWAY_TEAM_YELLOW_CARDS to TEXT,
            Match.AWAY_TEAM_RED_CARDS to TEXT,
            Match.AWAY_TEAM_LINEUP_SUBSTITUTE to TEXT

        )

        db.createTable(
            Team.FAVORITE_TEAM, true,
            Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,

            // Team Column
            Team.TEAM_ID to TEXT,
            Team.TEAM_NAME to TEXT,
            Team.TEAM_FORMED_YEAR to TEXT,
            Team.TEAM_STADUIM to TEXT,
            Team.TEAM_DESCRIPTION to TEXT

        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Match.FAVORITE_MATCH, true)
        db.dropTable(Team.FAVORITE_TEAM, true)
    }

}

val Context.database: FavoriteMatchDbHelper
    get() = FavoriteMatchDbHelper.getInstance(applicationContext)