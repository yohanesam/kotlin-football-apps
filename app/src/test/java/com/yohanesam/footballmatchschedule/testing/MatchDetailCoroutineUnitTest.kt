package com.yohanesam.footballmatchschedule.testing

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.entites.Match
import com.yohanesam.footballmatchschedule.model.responsesdata.MatchJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.presenter.coroutines.MatchDetailCoroutine
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchDetailCoroutineUnitTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var apiRepository: APIRepository

    @Mock
    private lateinit var gson: Gson


    private lateinit var matchDetailCoroutine: MatchDetailCoroutine

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        matchDetailCoroutine = MatchDetailCoroutine(view, apiRepository, gson)

    }

    @Test
    fun toGetSelectedMatchDetail() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchJSONArray(matches)
        val idMatch = "581600"

        doAsync {

            `when`(

                gson.fromJson(

                    apiRepository.doRequest(SportAPI.getSelectedMatch(idMatch)),
                    MatchJSONArray::class.java

                )

            ).thenReturn(response)

            matchDetailCoroutine.getSelectedMatch(idMatch)

            uiThread {

                Mockito.verify(view).isLoad()
                Mockito.verify(view).showResult(matches)
                Mockito.verify(view).stopLoad()

            }

        }
    }

}