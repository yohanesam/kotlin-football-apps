package com.yohanesam.footballmatchschedule.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.view.activities.SearchTeamActivity
import com.yohanesam.footballmatchschedule.model.entites.Team
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.coroutines.TeamCoroutine
import com.yohanesam.footballmatchschedule.view.activities.DetailOfTeamActivity
import com.yohanesam.footballmatchschedule.view.adapters.TeamRecycleAdapter
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import kotlinx.android.synthetic.main.activity_teams.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class TeamsFragment: Fragment(), TeamView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var teamCoroutine: TeamCoroutine
    private lateinit var adapter: TeamRecycleAdapter
    private var teams: MutableList<Team> = mutableListOf()

    private var isSearched: Boolean = false
    private var leagueName: String = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        srlTeamPullNavigateUpRefresh.setOnRefreshListener(this)
        srlTeamPullNavigateUpRefresh.setColorSchemeResources(

            android.R.color.holo_blue_dark,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_red_dark

        )

        adapter = TeamRecycleAdapter(this.context!!, teams) {

            startActivity<DetailOfTeamActivity>("TEAM_ID" to it.idTeam, "TEAM" to it)

        }

        rvTeamRecycleView.layoutManager = LinearLayoutManager(activity)
        rvTeamRecycleView.adapter = adapter

        val req = APIRepository()
        val gson = Gson()

        teamCoroutine = TeamCoroutine(this, req, gson)
        teamCoroutine.getTeamList(isSearched, getString(R.string.match_england))

        setLeagueList()

    }

    private fun setLeagueList() {


        val spinnerArrayAdapter = ArrayAdapter(context, R.layout.match_spinner_layout, resources.getStringArray(R.array.homeSpinnerMenu))
        spTeamHomeSpinner.adapter = spinnerArrayAdapter

        spTeamHomeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                leagueName = spTeamHomeSpinner.selectedItem.toString()
                isSearched = false

                teamCoroutine.getTeamList(isSearched, leagueName)

            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = "Search The team"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(searchedText: String?): Boolean {

                isSearched = true

                startActivity<SearchTeamActivity>(
                    "SEARCH_QUERY" to searchedText, "IS_SEARCHED" to isSearched
                )

                return false
            }

            override fun onQueryTextChange(searchedText: String?): Boolean {return false}

        })

        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.activity_teams, container, false)

    }

    override fun onRefresh() {

        if (srlTeamPullNavigateUpRefresh.isRefreshing) {
            srlTeamPullNavigateUpRefresh.isRefreshing = false
            loadTheData()
        }

    }

    override fun isLoad() {

        srlTeamPullNavigateUpRefresh.isRefreshing = true

    }

    override fun stopLoad() {

        srlTeamPullNavigateUpRefresh.isRefreshing = false

    }

    override fun showTeamResult(data: List<Team>?) {

        srlTeamPullNavigateUpRefresh.isRefreshing = false
        teams.clear()

        data?.let {
            teams.addAll(data)
            adapter.notifyDataSetChanged()
        } ?: toast("Error while showing the data. Please Refresh")
    }

    override fun showTeamResultForMatch(homeTeamData: List<Team>?, awayTeamData: List<Team>?) {

    }

    private fun loadTheData() {

        if (srlTeamPullNavigateUpRefresh.isRefreshing) return
        srlTeamPullNavigateUpRefresh.isRefreshing = true
        teamCoroutine.getTeamList(isSearched, leagueName)

    }

}