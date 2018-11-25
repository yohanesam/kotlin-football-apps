package com.yohanesam.footballmatchschedule.presenter.apis

import com.yohanesam.footballmatchschedule.model.entites.Match
import com.yohanesam.footballmatchschedule.model.entites.Team
import com.yohanesam.footballmatchschedule.model.responsesdata.MatchJSONArray
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIServices {
    @GET("eventspastleague.php?id=4399")
    fun getLastMatch(): Observable<MatchJSONArray>

    @GET("eventsnextleague.php?id=4399")
    fun getNextMatch(): Observable<MatchJSONArray>

    @GET("lookupevent.php?id={matchId}")
    fun getSelectedMatch(@Path("matchId") matchId: String?): Observable<Match>

    @GET("lookupteam.php?id={teamId}")
    fun getSelectedTeam(@Path("teamId") teamId: String?): Observable<Team>

    companion object Factory {
        fun create(): APIServices {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                .build()

            return retrofit.create(APIServices::class.java)
        }
    }
}