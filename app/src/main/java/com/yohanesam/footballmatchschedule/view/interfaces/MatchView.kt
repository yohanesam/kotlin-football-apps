package com.yohanesam.footballmatchschedule.view.interfaces

import com.yohanesam.footballmatchschedule.model.Entity.Match

interface MatchView {

    fun isLoad()
    fun stopLoad()
    fun showResult(data: List<Match>?)

}