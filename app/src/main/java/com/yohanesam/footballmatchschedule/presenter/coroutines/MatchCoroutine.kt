package com.yohanesam.footballmatchschedule.presenter.coroutines

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.MatchJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchCoroutine(
    private val view: MatchView,
    private val apiRepository: APIRepository,
    private val gson: Gson
) {

    fun getMatchList(isNextMatch: Boolean) {

        view.isLoad()

        if (!isNextMatch) {
            GlobalScope.launch(Dispatchers.Main) {
                val data = gson.fromJson(
                    apiRepository.doRequest(SportAPI.getLastMatches()).await(),
                    MatchJSONArray::class.java
                )

                view.showResult(data.arrMatchesResult)
                view.stopLoad()
            }
        }

        else {
            GlobalScope.launch(Dispatchers.Main) {
                val data = gson.fromJson(
                    apiRepository.doRequest(SportAPI.getNextMatches()).await(),
                    MatchJSONArray::class.java
                )

                view.showResult(data.arrMatchesResult)
                view.stopLoad()
            }
        }
    }
}