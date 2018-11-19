package com.yohanesam.footballmatchschedule.presenter.coroutines

import android.util.Log
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.responsesdata.MatchJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchCoroutine(
    private val view: MatchView,
    private val apiRepository: APIRepository,
    private val gson: Gson
) {

    fun getMatchList(isNextMatch: Boolean): Unit {

        view.isLoad()

        if (!isNextMatch) {
            async(UI) {
                val data = bg {
                    gson.fromJson(
                        apiRepository.doRequest(SportAPI.getLastMatches()),
                        MatchJSONArray::class.java
                    )
                }

                Log.d("TAG", data.await().arrMatchesResult.toString())
                view.showResult(data.await().arrMatchesResult)
                view.stopLoad()
            }
        } else {
            async(UI) {
                val data = bg {
                    gson.fromJson(
                        apiRepository.doRequest(SportAPI.getNextMatches()),
                        MatchJSONArray::class.java
                    )
                }

                view.showResult(data.await().arrMatchesResult)
                view.stopLoad()
            }
        }
    }
}