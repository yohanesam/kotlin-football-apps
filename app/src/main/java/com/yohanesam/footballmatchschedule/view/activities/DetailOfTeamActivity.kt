package com.yohanesam.footballmatchschedule.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.model.entites.Team
import com.yohanesam.footballmatchschedule.presenter.apis.APIRepository
import com.yohanesam.footballmatchschedule.presenter.coroutines.TeamDetailCoroutine
import com.yohanesam.footballmatchschedule.presenter.localpresenter.FavoriteTeamPresenter
import com.yohanesam.footballmatchschedule.view.adapters.TeamDetailFragmentAdapter
import com.yohanesam.footballmatchschedule.view.interfaces.TeamView
import kotlinx.android.synthetic.main.activity_detail_of_team.*

class DetailOfTeamActivity : AppCompatActivity(), TeamView {

    private lateinit var idTeam: String

    private lateinit var pagerAdapterTeams: TeamDetailFragmentAdapter
    private lateinit var teamDetailCoroutine: TeamDetailCoroutine
    private lateinit var favoriteTeamPresenter: FavoriteTeamPresenter
    private lateinit var data: Team

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_of_team)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        idTeam = intent.getStringExtra("TEAM_ID")
        data = intent.getParcelableExtra("TEAM")

        Log.d("DATA", idTeam)

        val req = APIRepository()
        val gson = Gson()

        teamDetailCoroutine = TeamDetailCoroutine(this, req, gson)
        favoriteTeamPresenter = FavoriteTeamPresenter(this, this)

        teamDetailCoroutine.getSelectedTeam(idTeam)
        isFavorite = favoriteTeamPresenter.isFavorite(this, data)

        setFragmentView()

    }

    private fun setFragmentView() {

        pagerAdapterTeams = TeamDetailFragmentAdapter(idTeam, supportFragmentManager)
        vpTeamDetailViewPager.adapter = pagerAdapterTeams
        tlTeamDetailTabLayout.setupWithViewPager(vpTeamDetailViewPager)

    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        setFavorite()

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.addToFavorite -> {
                if (!isFavorite) {

                    favoriteTeamPresenter.addToFavorite(pbProgressDetailTeamActivity, this, data)

                } else {

                    favoriteTeamPresenter.removeFromFavorite(pbProgressDetailTeamActivity, this, data)

                }

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }

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

        val teamListData = Team(

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

    private fun setFavorite() {

        if (isFavorite) {

            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_yes)
            Log.d("FAVORITETRUE", "added to favorite")

        } else {

            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_no)
            Log.d("FAVORITEFALSE", "removed from favorite")

        }

    }

}
