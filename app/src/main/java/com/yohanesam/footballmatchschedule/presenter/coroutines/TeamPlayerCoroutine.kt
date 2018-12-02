package com.yohanesam.footballmatchschedule.presenter.coroutines

import android.util.Log
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.PlayerJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.PlayerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamPlayerCoroutine(
    private val view: PlayerView,
    private val apiRepository: APIRepository,
    private val gson: Gson
) {

    fun getTeamPlayer(idTeam: String) {

        view.isLoad()

        doAsync {

            val data = gson.fromJson(
                apiRepository.doRequest(SportAPI.getPlayersFromTeam(idTeam)),
                PlayerJSONArray::class.java
            )

            uiThread {

                view.showPlayerResult(data.jsonArrayPlayersFromTeam)
                view.stopLoad()

            }

        }

    }

    fun getSelectedTeamPlayer(idPlayer: String) {

        view.isLoad()

        doAsync {

            val data = gson.fromJson(
                apiRepository.doRequest(SportAPI.getSelectedPlayer(idPlayer)),
                PlayerJSONArray::class.java
            )

            uiThread {

                view.showPlayerResult(data.jsonArrayPlayer)
                view.stopLoad()

            }

        }

    }

}