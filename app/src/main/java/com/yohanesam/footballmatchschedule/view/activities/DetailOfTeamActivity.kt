package com.yohanesam.footballmatchschedule.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.model.entites.Team
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.coroutines.TeamDetailCoroutine
import com.yohanesam.footballmatchschedule.view.adapters.TeamDetailFragmentAdapter
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import kotlinx.android.synthetic.main.activity_detail_of_team.*

class DetailOfTeamActivity : AppCompatActivity(), TeamView {

    private lateinit var idTeam: String
    private lateinit var teamName: String

    private lateinit var pagerAdapterTeams: TeamDetailFragmentAdapter
    private lateinit var teamDetailCoroutine: TeamDetailCoroutine
    private lateinit var teamListData: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_of_team)

        setFragmentView()

        idTeam = intent.getStringExtra("TEAM_ID")

        Log.d("DATA", idTeam)

        val req = APIRepository()
        val gson = Gson()

        teamDetailCoroutine = TeamDetailCoroutine(this, req, gson)
        teamDetailCoroutine.getSelectedTeam(idTeam)

    }

    private fun setFragmentView() {

        pagerAdapterTeams = TeamDetailFragmentAdapter(idTeam, supportFragmentManager)
        vpTeamDetailViewPager.adapter = pagerAdapterTeams
        tlTeamDetailTabLayout.setupWithViewPager(vpTeamDetailViewPager)

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
        tvTeamName.text = teamListData.strTeam
        tvTeamStadium.text = teamListData.strStadium
        tvTeamFormed.text = teamListData.intFormedYear

        Glide.with(this).load(teamListData.strTeamBadge).into(ivTeamDetailBadge)


    }

}
