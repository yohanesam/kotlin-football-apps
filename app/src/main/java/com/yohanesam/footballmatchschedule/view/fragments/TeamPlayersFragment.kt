package com.yohanesam.footballmatchschedule.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.model.entites.Player
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.coroutines.TeamPlayerCoroutine
import com.yohanesam.footballmatchschedule.view.adapters.TeamPlayerRecycleAdapter
import com.yohanesam.footballmatchschedule.view.interfaces.PlayerView
import kotlinx.android.synthetic.main.team_players_fragment_activity.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class TeamPlayersFragment: Fragment(), PlayerView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var idTeam: String
    private lateinit var adapter: TeamPlayerRecycleAdapter
    private lateinit var playerCoroutine: TeamPlayerCoroutine

    private var players: MutableList<Player> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        srlTeamPlayerPullNavigateUpRefresh.setOnRefreshListener(this)
        srlTeamPlayerPullNavigateUpRefresh.setColorSchemeResources(

            android.R.color.holo_blue_dark,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_red_dark

        )

        val bindItem = arguments
        idTeam = bindItem?.getString("TEAM_PLAYERS") ?: "idTeam"

        adapter = TeamPlayerRecycleAdapter(this.context!!, players) {



        }

        rvTeamPlayerRecycleView.layoutManager = LinearLayoutManager(activity)
        rvTeamPlayerRecycleView.adapter = adapter

        val req = APIRepository()
        val gson = Gson()

        playerCoroutine = TeamPlayerCoroutine(this, req, gson)
        playerCoroutine.getTeamPlayer(idTeam)

    }

    override fun onRefresh() {

        playerCoroutine.getTeamPlayer(idTeam)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.team_players_fragment_activity, container, false)

    }

    override fun isLoad() {

        srlTeamPlayerPullNavigateUpRefresh.isRefreshing = true

    }

    override fun stopLoad() {

        srlTeamPlayerPullNavigateUpRefresh.isRefreshing = false

    }

    override fun showPlayerResult(data: List<Player>?) {

        srlTeamPlayerPullNavigateUpRefresh.isRefreshing = false
        players.clear()

        data?.let {

            players.addAll(data)
            adapter.notifyDataSetChanged()

        } ?: toast("Error while showing the data. Please Refresh")

    }


}