package com.yohanesam.footballmatchschedule.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.model.entites.Match
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.coroutines.MatchCoroutine
import com.yohanesam.footballmatchschedule.view.Adapter.MatchRecycleAdapter
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast

class SearchMatchActivity : AppCompatActivity(), MatchView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: MatchRecycleAdapter
    private lateinit var matchCoroutine: MatchCoroutine
    private lateinit var searchQuery: String

    private var matches: MutableList<Match> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Search Result"

        searchQuery = intent.getStringExtra("SEARCH_QUERY")

        srlSearchMatchPullNavigateUpRefresh.setOnRefreshListener(this)
        srlSearchMatchPullNavigateUpRefresh.setColorSchemeResources(

            android.R.color.holo_blue_dark,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_red_dark

        )

        adapter = MatchRecycleAdapter(this, matches) {
            startActivity<DetailOfTheMatch>(
                "ID_MATCH" to it.idEvent,
                "ID_HOME_TEAM" to it.idHomeTeam,
                "ID_AWAY_TEAM" to it.idAwayTeam,
                "EVENT" to it
            )
        }

        rvSearchMatchRecycleView.layoutManager = LinearLayoutManager(this)
        rvSearchMatchRecycleView.adapter = adapter

        val req = APIRepository()
        val gson = Gson()

        matchCoroutine = MatchCoroutine(this, req, gson)
        matchCoroutine.getSearchedMatchList(searchQuery)

    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true

    }

    override fun onRefresh() {

        matchCoroutine.getSearchedMatchList(searchQuery)

    }

    override fun isLoad() {

        srlSearchMatchPullNavigateUpRefresh.isRefreshing = true

    }

    override fun stopLoad() {

        srlSearchMatchPullNavigateUpRefresh.isRefreshing = false

    }

    override fun showResult(data: List<Match>?) {

        srlSearchMatchPullNavigateUpRefresh.isRefreshing = false
        matches.clear()

        data?.let {

            matches.addAll(data)
            adapter.notifyDataSetChanged()

        } ?: toast("Error while showing the data. Please Refresh")

    }

}
