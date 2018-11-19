package com.yohanesam.footballmatchschedule.presenter.apis

object SportAPI {
    fun getLastMatches(): String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4399"
    }

    fun getNextMatches(): String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4399"
    }

    fun getSelectedMatch(matchId: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=$matchId"
    }

    fun getSelectedTeam(teamId: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=$teamId"
    }
}