package com.yohanesam.footballmatchschedule.presenter.apis

object SportAPI {

    fun getLastMatches(leagueId: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=$leagueId"
    }

    fun getNextMatches(leagueId: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=$leagueId"
    }

    fun getSelectedMatch(matchId: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=$matchId"
    }

    fun getSelectedTeam(teamId: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=$teamId"
    }
}