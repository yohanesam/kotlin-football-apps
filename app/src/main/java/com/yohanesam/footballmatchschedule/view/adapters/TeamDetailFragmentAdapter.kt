package com.yohanesam.footballmatchschedule.view.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yohanesam.footballmatchschedule.view.fragments.TeamDetailDescriptionFragment
import com.yohanesam.footballmatchschedule.view.fragments.TeamPlayersFragment

class TeamDetailFragmentAdapter(
    private val teamId: String,
    fragMan: FragmentManager
) : FragmentPagerAdapter(fragMan) {

    private val count = 2

    override fun getItem(position: Int): Fragment? {

        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = newInstanceTeamDesc(teamId)
            1 -> fragment = newInstanceTeamPlayer(teamId)
        }

        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return if (position == 0) {
            "Description"
        } else {
            "All Player"
        }

    }

    override fun getCount(): Int {

        return count

    }

    companion object {

        const val TEAM_DESC = "TEAM_DESC"
        const val TEAM_PLAYERS = "TEAM_PLAYERS"

        fun newInstanceTeamDesc(idTeam: String): TeamDetailDescriptionFragment {

            val bindData = Bundle()
            bindData.putString("TEAM_DESC", idTeam)

            val teamDescFrag = TeamDetailDescriptionFragment()
            teamDescFrag.arguments  = bindData

            return teamDescFrag

        }

        fun newInstanceTeamPlayer(idTeam: String): TeamPlayersFragment {

            val bindData = Bundle()
            bindData.putString("TEAM_PLAYERS", idTeam)

            val teamPlayersFrag = TeamPlayersFragment()
            teamPlayersFrag.arguments  = bindData

            return teamPlayersFrag

        }

    }

}