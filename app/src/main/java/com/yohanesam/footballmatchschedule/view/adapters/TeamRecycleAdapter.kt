package com.yohanesam.footballmatchschedule.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.model.entites.Team
import kotlinx.android.synthetic.main.team_item_row.view.*

class TeamRecycleAdapter(
    private val context: Context,
    private val teams: List<Team>,
    private val listener: (Team) -> Unit
) :
    RecyclerView.Adapter<TeamRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return TeamRecycleAdapter.ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.team_item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(teams[position], listener)
    }

    class ViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bindData(teams: Team, listener: (Team) -> Unit) {

            containerView.tvTeamName.text = teams.strTeam
            Glide.with(containerView).load(teams.strTeamBadge).into(containerView.ivTeamBadge)

            containerView.setOnClickListener { listener(teams) }
        }

    }

}
