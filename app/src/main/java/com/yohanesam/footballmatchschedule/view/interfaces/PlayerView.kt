package com.yohanesam.footballmatchschedule.view.interfaces

import com.yohanesam.footballmatchschedule.model.entites.Player

interface PlayerView {

    fun isLoad()
    fun stopLoad()
    fun showTeamResult(data: List<Player>?)

}