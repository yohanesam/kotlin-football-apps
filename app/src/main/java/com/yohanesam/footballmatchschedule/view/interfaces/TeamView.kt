package com.yohanesam.footballmatchschedule.view.interfaces

import com.yohanesam.footballmatchschedule.model.entites.Team

interface TeamView {

    fun isLoad()
    fun stopLoad()
    fun showTeamResultForMatch(homeTeamData: List<Team>?, awayTeamData: List<Team>?)
    fun showTeamResult(data: List<Team>?)

}