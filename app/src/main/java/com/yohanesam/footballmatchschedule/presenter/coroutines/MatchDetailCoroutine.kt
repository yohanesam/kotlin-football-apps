package com.yohanesam.footballmatchschedule.presenter.coroutines

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.MatchJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchDetailCoroutine(
    val view: MatchView,
    val apiRepository: APIRepository,
    val gson: Gson
) {

    fun getSelectedMatch(matchId: String?) {

        view.isLoad()

        async(UI) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(SportAPI.getSelectedMatch(matchId)),
                    MatchJSONArray::class.java
                )
            }

            view.showResult(data.await().arrMatchesResult)
            view.stopLoad()

        }

    }

}