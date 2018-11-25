package com.yohanesam.footballmatchschedule.view.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yohanesam.footballmatchschedule.model.entites.Match
import com.yohanesam.footballmatchschedule.R
import kotlinx.android.synthetic.main.match_schedule_row.view.*

class MatchRecycleAdapter(
    private val context: Context,
    private val matches: List<Match>,
    private val listener: (Match) -> Unit
) :
    RecyclerView.Adapter<MatchRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.match_schedule_row, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(matches[position], listener)

    }

    class ViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bindData(match: Match, listener: (Match) -> Unit) {
            containerView.tvMatchScheduleDate.text = match.dateEvent

            if (match.intHomeScore == null) {
                match.intHomeScore = ""
                match.intAwayScore = ""
            }

            containerView.tvHomeTeam.text = match.strHomeTeam
            containerView.tvAwayTeam.text = match.strAwayTeam
            containerView.tvScoreOfTheMatch.text = match.intHomeScore + " - " + match.intAwayScore

            containerView.setOnClickListener { listener(match) }

        }

    }
}