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

    fun getSearchedMatches(searchKey: String): String {
        return "https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e=$searchKey"
    }

    fun getTeamsFromLeague(leagueName: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=$leagueName"
    }

    fun getSelectedTeam(teamId: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=$teamId"
    }

    fun getSearchedTeam(searchKey: String?): String {
        return "https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=$searchKey"
    }

    fun getPlayersFromTeam(teamId: String?): String  {
        return "https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id=$teamId"
    }

    fun getSelectedPlayer(playerId: String?): String  {
        return "https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id=$playerId"
    }

}