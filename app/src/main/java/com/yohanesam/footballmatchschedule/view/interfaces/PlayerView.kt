package com.yohanesam.footballmatchschedule.view.interfaces

import com.yohanesam.footballmatchschedule.model.entites.Player

interface PlayerView {

    fun isLoad()
    fun stopLoad()
    fun showPlayerResult(data: List<Player>?)

}