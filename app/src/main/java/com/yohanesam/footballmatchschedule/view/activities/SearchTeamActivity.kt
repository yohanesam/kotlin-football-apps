package com.yohanesam.footballmatchschedule.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.model.entites.Team
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.coroutines.TeamCoroutine
import com.yohanesam.footballmatchschedule.view.adapters.TeamRecycleAdapter
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.toast

class SearchTeamActivity : AppCompatActivity(), TeamView {

    private lateinit var adapter: TeamRecycleAdapter
    private lateinit var teamCoroutine: TeamCoroutine
    private lateinit var searchQuery: String

    private var isSearched: Boolean = false
    private var teams: MutableList<Team> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Search Result"

        searchQuery = intent.getStringExtra("SEARCH_QUERY")
        isSearched = intent.getBooleanExtra("IS_SEARCHED", false)

        srlSearchTeamPullNavigateUpRefresh.setColorSchemeResources(

            android.R.color.holo_blue_dark,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_red_dark

        )

        adapter = TeamRecycleAdapter(this, teams) {

        }

        rvSearchTeamRecycleView.layoutManager = LinearLayoutManager(this)
        rvSearchTeamRecycleView.adapter = adapter

        val req = APIRepository()
        val gson = Gson()

        teamCoroutine = TeamCoroutine(this, req, gson)
        teamCoroutine.getTeamList(isSearched, searchQuery)

    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true

    }

    override fun isLoad() {

        srlSearchTeamPullNavigateUpRefresh.isRefreshing = true

    }

    override fun stopLoad() {

        srlSearchTeamPullNavigateUpRefresh.isRefreshing = false

    }

    override fun showTeamResultForMatch(homeTeamData: List<Team>?, awayTeamData: List<Team>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTeamResult(data: List<Team>?) {

        srlSearchTeamPullNavigateUpRefresh.isRefreshing = false
        teams.clear()

        data?.let {
            teams.addAll(data)
            adapter.notifyDataSetChanged()
        } ?: toast("Error while showing the data. Please Refresh")

    }


}
