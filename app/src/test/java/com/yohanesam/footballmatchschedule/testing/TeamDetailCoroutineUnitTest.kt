package com.yohanesam.footballmatchschedule.testing

import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.entites.Team
import com.yohanesam.footballmatchschedule.model.responsesdata.TeamJSONArray
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.apis.SportAPI
import com.yohanesam.footballmatchschedule.presenter.coroutines.TeamDetailCoroutine
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

internal class TeamDetailCoroutineUnitTest {

    @Mock
    private lateinit var view: TeamView

    @Mock
    private lateinit var apiRepository: APIRepository

    @Mock
    private lateinit var gson: Gson


    private lateinit var teamDetailCoroutine: TeamDetailCoroutine

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        teamDetailCoroutine = TeamDetailCoroutine(view, apiRepository, gson)
    }

    @Test
    fun toGetTeamDetail() {

        val homeTeam: MutableList<Team> = mutableListOf()
        val awayTeam: MutableList<Team> = mutableListOf()

        val homeResponse = TeamJSONArray(homeTeam)
        val awayResponse = TeamJSONArray(awayTeam)

        val idHomeTeam = "133852"
        val idAwayTeam = "133877"

        doAsync {

            `when`(

                gson.fromJson(

                    apiRepository.doRequest(SportAPI.getSelectedTeam(idHomeTeam)),
                    TeamJSONArray::class.java

                )

            ).thenReturn(homeResponse)

            `when`(

                gson.fromJson(

                    apiRepository.doRequest(SportAPI.getSelectedTeam(idAwayTeam)),
                    TeamJSONArray::class.java

                )

            ).thenReturn(awayResponse)

            teamDetailCoroutine.getSelectedTeamForMatch(idHomeTeam, idAwayTeam)

            uiThread {

                Mockito.verify(view).isLoad()
                Mockito.verify(view).showTeamResultForMatch(homeTeam, awayTeam)
                Mockito.verify(view).stopLoad()

            }

        }

    }

}