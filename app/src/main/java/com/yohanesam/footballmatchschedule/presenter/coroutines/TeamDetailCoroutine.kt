package com.yohanesam.footballmatchschedule.presenter.coroutines

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.TeamJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailCoroutine(
    private val view: TeamView,
    private val apiRepository: APIRepository,
    private val gson: Gson
) {

    fun getSelectedTeam(idHomeTeam: String, idAwayTeam: String) {

        view.isLoad()

        GlobalScope.launch(Dispatchers.Main) {
            val homeTeamData = gson.fromJson(
                    apiRepository.doRequest(SportAPI.getSelectedTeam(idHomeTeam)).await(),
                    TeamJSONArray::class.java
            )

            val awayTeamData = gson.fromJson(
                    apiRepository.doRequest(SportAPI.getSelectedTeam(idAwayTeam)).await(),
                    TeamJSONArray::class.java
                )

            view.showTeamResult(homeTeamData.jsonArrayTeamResult, awayTeamData.jsonArrayTeamResult)
            view.stopLoad()
        }

    }

}