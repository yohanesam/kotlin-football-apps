package com.yohanesam.footballmatchschedule.presenter.apis

import java.net.URL

class APIRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}