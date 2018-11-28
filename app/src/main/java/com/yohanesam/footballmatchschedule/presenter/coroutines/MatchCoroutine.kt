package com.yohanesam.footballmatchschedule.presenter.coroutines

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.MatchJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchCoroutine(
    private val view: MatchView,
    private val apiRepository: APIRepository,
    private val gson: Gson
) {

    fun getMatchList(isNextMatch: Boolean, idLeague: String?) {

        view.isLoad()

        if (!isNextMatch) {

            doAsync {

                val data = gson.fromJson(
                    apiRepository.doRequest(SportAPI.getLastMatches(idLeague)),
                    MatchJSONArray::class.java
                )

                uiThread {
                    view.showResult(data.arrMatchesResult)
                    view.stopLoad()
                }
            }

        } else {

            doAsync {

                val data = gson.fromJson(
                    apiRepository.doRequest(SportAPI.getNextMatches(idLeague)),
                    MatchJSONArray::class.java
                )

                uiThread {
                    view.showResult(data.arrMatchesResult)
                    view.stopLoad()
                }
            }

        }
    }

}