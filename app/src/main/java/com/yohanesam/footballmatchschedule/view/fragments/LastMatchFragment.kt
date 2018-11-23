package com.yohanesam.footballmatchschedule.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.model.Entity.Match
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.coroutines.MatchCoroutine
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.view.activities.DetailOfTheMatch
import com.yohanesam.footballmatchschedule.view.Adapter.MatchRecycleAdapter
import com.yohanesam.footballmatchschedule.view.activities.HomeActivity
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.match_fragment_activity.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class LastMatchFragment : Fragment(), MatchView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var matchCoroutine: MatchCoroutine
    private lateinit var adapter: MatchRecycleAdapter
    private lateinit var spinnerArrayAdapter: ArrayAdapter<String>
    private var matches: MutableList<Match> = mutableListOf()

    @SuppressLint("ResourceAsColor")
    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        srlMatchPullNavigateUpRefresh.setOnRefreshListener(this)
        srlMatchPullNavigateUpRefresh.setColorSchemeResources(

            android.R.color.holo_blue_dark,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_red_dark

        )

        adapter = MatchRecycleAdapter(this.context!!, matches) {
            startActivity<DetailOfTheMatch>(
                "ID_MATCH" to it.idEvent, "ID_HOME_TEAM" to it.idHomeTeam, "ID_AWAY_TEAM" to it.idAwayTeam, "EVENT" to it
            )
        }

        rvMatchRecycleView.layoutManager = LinearLayoutManager(activity)
        rvMatchRecycleView.adapter = adapter



        val req = APIRepository()
        val gson = Gson()

        matchCoroutine = MatchCoroutine(this, req, gson)
        setLeagueList()
    }


    private fun setLeagueList() {

        spinnerArrayAdapter = ArrayAdapter(context, R.layout.match_spinner_layout, resources.getStringArray(R.array.homeSpinnerMenu))
        spHomeSpinner.adapter = spinnerArrayAdapter

        spHomeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

                spHomeSpinner.setSelection(spinnerArrayAdapter.getPosition("England Premier League"))
                matchCoroutine.getMatchList(false, "4328")

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                when(position) {
                    0 -> matchCoroutine.getMatchList(false, "4328")
                    1 -> matchCoroutine.getMatchList(false, "4335")
                    2 -> matchCoroutine.getMatchList(false, "4332")
                    3 -> matchCoroutine.getMatchList(false, "4331")
                }


            }

        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.match_fragment_activity, container, false)

    }

    override fun onRefresh() {

        setLeagueList()

    }

    override fun isLoad() {

        srlMatchPullNavigateUpRefresh.isRefreshing = true

    }

    override fun stopLoad() {

        srlMatchPullNavigateUpRefresh.isRefreshing = false

    }

    override fun showResult(data: List<Match>?) {

        srlMatchPullNavigateUpRefresh.isRefreshing = false
        matches.clear()

        data?.let {
            matches.addAll(data)
            adapter.notifyDataSetChanged()
        } ?: toast("data = $data")

    }

}