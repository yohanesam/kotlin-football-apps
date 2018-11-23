package com.yohanesam.footballmatchschedule.presenter.coroutines

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.MatchJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailCoroutine(
    val view: MatchView,
    val apiRepository: APIRepository,
    val gson: Gson
) {

    fun getSelectedMatch(matchId: String?) {

        view.isLoad()

        doAsync {

            val data = gson.fromJson(
                apiRepository.doRequest(SportAPI.getSelectedMatch(matchId)),
                MatchJSONArray::class.java
            )

            uiThread {
                view.showResult(data.arrMatchesResult)
                view.stopLoad()
            }
        }

    }

}