package com.yohanesam.footballmatchschedule.testing

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.entites.Match
import com.yohanesam.footballmatchschedule.model.responsesdata.MatchJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.presenter.coroutines.MatchCoroutine
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchCoroutineUnitTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var apiRepository: APIRepository

    @Mock
    private lateinit var gson: Gson


    private lateinit var matchCoroutine: MatchCoroutine

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        matchCoroutine = MatchCoroutine(view, apiRepository, gson)
    }

    @Test
    fun testToGetMatchList() {

        val matches: MutableList<Match> = mutableListOf()
        val response = MatchJSONArray(matches)
        val idLeague = "4328"

        doAsync {

            `when`(

                gson.fromJson(

                    apiRepository.doRequest(SportAPI.getLastMatches(idLeague)),
                    MatchJSONArray::class.java

                )

            ).thenReturn(response)

            matchCoroutine.getMatchList(false, idLeague)

            uiThread {

                Mockito.verify(view).isLoad()
                Mockito.verify(view).showResult(matches)
                Mockito.verify(view).stopLoad()

            }

        }
    }

}