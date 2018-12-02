package com.yohanesam.footballmatchschedule.presenter.coroutines

import android.util.Log
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.TeamJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailCoroutine(
    private val view: TeamView,
    private val apiRepository: APIRepository,
    private val gson: Gson
) {

    fun getSelectedTeamForMatch(idHomeTeam: String, idAwayTeam: String) {

        view.isLoad()

        doAsync {

            val homeTeamData = gson.fromJson(
                apiRepository.doRequest(SportAPI.getSelectedTeam(idHomeTeam)),
                TeamJSONArray::class.java
            )

            val awayTeamData = gson.fromJson(
                apiRepository.doRequest(SportAPI.getSelectedTeam(idAwayTeam)),
                TeamJSONArray::class.java
            )

            uiThread {

                view.showTeamResultForMatch(homeTeamData.jsonArrayTeamResult, awayTeamData.jsonArrayTeamResult)
                view.stopLoad()

            }
        }

    }

    fun getSelectedTeam(idTeam: String) {

        view.isLoad()

        doAsync {

            val data = gson.fromJson(
                apiRepository.doRequest(SportAPI.getSelectedTeam(idTeam)),
                TeamJSONArray::class.java
            )

            uiThread {
                Log.d("DATA", data.jsonArrayTeamResult.toString())
                view.showTeamResult(data.jsonArrayTeamResult)
                view.stopLoad()

            }

        }

    }

}