package com.yohanesam.footballmatchschedule.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.model.entites.Match
import com.yohanesam.footballmatchschedule.presenter.localpresenter.FavoriteMatchPresenter
import com.yohanesam.footballmatchschedule.view.activities.DetailOfFavoriteMatch
import com.yohanesam.footballmatchschedule.view.adapters.FavoriteMatchRecycleAdapter
import com.yohanesam.footballmatchschedule.view.interfaces.MatchView
import kotlinx.android.synthetic.main.activity_favorites.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FavoriteMatchFragment : Fragment(), MatchView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var favoriteMatchPresenter: FavoriteMatchPresenter
    private lateinit var adapterMatch: FavoriteMatchRecycleAdapter
    private var matches: MutableList<Match> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        srlFavoritePullNavigateUpRefresh.setOnRefreshListener(this)
        srlFavoritePullNavigateUpRefresh.setColorSchemeResources(

            android.R.color.holo_blue_dark,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_red_dark

        )

        adapterMatch = FavoriteMatchRecycleAdapter(this.context!!, matches) {
            startActivity<DetailOfFavoriteMatch>(
                "ID_MATCH" to it.idEvent,
                "ID_HOME_TEAM" to it.idHomeTeam,
                "ID_AWAY_TEAM" to it.idAwayTeam,
                "EVENT" to it
            )
        }

        rvFavoriteRecycleView.layoutManager = LinearLayoutManager(activity)
        rvFavoriteRecycleView.adapter = adapterMatch

        favoriteMatchPresenter = FavoriteMatchPresenter(this.context!!, this)
        favoriteMatchPresenter.getFavoriteMatch()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_favorites, container, false)

    }

    override fun onRefresh() {

        favoriteMatchPresenter.getFavoriteMatch()

    }

    override fun isLoad() {

        srlFavoritePullNavigateUpRefresh.isRefreshing = true

    }

    override fun stopLoad() {

        srlFavoritePullNavigateUpRefresh.isRefreshing = false

    }

    override fun showResult(data: List<Match>?) {

        srlFavoritePullNavigateUpRefresh.isRefreshing = false
        matches.clear()

        data?.let {
            matches.addAll(data)
            adapterMatch.notifyDataSetChanged()
        } ?: toast("Error while showing the data. Please Refresh")

    }

}