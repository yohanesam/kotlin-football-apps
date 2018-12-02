package com.yohanesam.footballmatchschedule.presenter.coroutines

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.TeamJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamCoroutine(
    private val view: TeamView,
    private val apiRepository: APIRepository,
    private val gson: Gson
) {

    fun getTeamList(isSearched: Boolean, leagueName: String) {

        view.isLoad()

        if (!isSearched) {
            doAsync {

                val data = gson.fromJson(
                    apiRepository.doRequest(SportAPI.getTeamsFromLeague(leagueName)),
                    TeamJSONArray::class.java
                )


                uiThread {
                    view.showTeamResult(data.jsonArrayTeamResult)
                    view.stopLoad()
                }
            }
        } else {

            doAsync {

                val data = gson.fromJson(
                    apiRepository.doRequest(SportAPI.getSearchedTeam(leagueName)),
                    TeamJSONArray::class.java
                )

                uiThread {
                    view.showTeamResult(data.jsonArrayTeamResult)
                    view.stopLoad()
                }
            }

        }


    }

}