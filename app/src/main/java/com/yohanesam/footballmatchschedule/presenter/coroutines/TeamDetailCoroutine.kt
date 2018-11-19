package com.yohanesam.footballmatchschedule.presenter.coroutines

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.TeamJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamDetailCoroutine(
    private val view: TeamView,
    private val apiRepository: APIRepository,
    private val gson: Gson
) {

    fun getSelectedTeam(idHomeTeam: String, idAwayTeam: String) {

        view.isLoad()

        async(UI) {
            val homeTeamData = bg {
                gson.fromJson(
                    apiRepository.doRequest(SportAPI.getSelectedTeam(idHomeTeam)),
                    TeamJSONArray::class.java
                )
            }

            val awayTeamData = bg {
                gson.fromJson(
                    apiRepository.doRequest(SportAPI.getSelectedTeam(idAwayTeam)),
                    TeamJSONArray::class.java
                )
            }

            view.showTeamResult(homeTeamData.await().jsonArrayTeamResult, awayTeamData.await().jsonArrayTeamResult)
            view.stopLoad()
        }

    }

}