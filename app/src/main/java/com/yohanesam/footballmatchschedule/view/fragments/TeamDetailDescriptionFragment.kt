package com.yohanesam.footballmatchschedule.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.model.entites.Team
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.coroutines.TeamDetailCoroutine
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import kotlinx.android.synthetic.main.activity_detail_of_team.*
import kotlinx.android.synthetic.main.team_desc_item_row.*

class TeamDetailDescriptionFragment: Fragment(), TeamView {

    private lateinit var idTeam: String
    private lateinit var teamListData: Team
    private lateinit var teamDetailCoroutine: TeamDetailCoroutine

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        val bindItem = arguments
        idTeam = bindItem?.getString("TEAM_DESC") ?: "idTeam"

        val req = APIRepository()
        val gson= Gson()

        teamDetailCoroutine = TeamDetailCoroutine(this, req, gson)
        teamDetailCoroutine.getSelectedTeam(idTeam)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.team_desc_item_row, container, false)

    }

    override fun isLoad() {

        pbProgressDetailTeamActivity.visibility = View.VISIBLE

    }

    override fun stopLoad() {

        pbProgressDetailTeamActivity.visibility = View.GONE

    }

    override fun showTeamResultForMatch(homeTeamData: List<Team>?, awayTeamData: List<Team>?) {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun showTeamResult(data: List<Team>?) {

        teamListData = Team(

            data?.get(0)?.id,
            data?.get(0)?.idTeam,
            data?.get(0)?.strTeam,
            data?.get(0)?.strTeamBadge,
            data?.get(0)?.intFormedYear,
            data?.get(0)?.strStadium,
            data?.get(0)?.strDescriptionEN

        )

        tvTeamDesc.text = teamListData.strDescriptionEN

    }

}