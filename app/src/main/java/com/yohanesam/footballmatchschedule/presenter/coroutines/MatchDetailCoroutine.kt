package com.yohanesam.footballmatchschedule.presenter.coroutines

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.MatchJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailCoroutine(
    val view: MatchView,
    val apiRepository: APIRepository,
    val gson: Gson
) {

    fun getSelectedMatch(matchId: String?) {

        view.isLoad()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository.doRequest(SportAPI.getSelectedMatch(matchId)).await(),
                MatchJSONArray::class.java
            )

            view.showResult(data.arrMatchesResult)
            view.stopLoad()
        }

    }

}