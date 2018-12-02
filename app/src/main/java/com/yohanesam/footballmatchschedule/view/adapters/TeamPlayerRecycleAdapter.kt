package com.yohanesam.footballmatchschedule.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.model.entites.Player
import kotlinx.android.synthetic.main.team_players_item_row.view.*
import java.text.FieldPosition

class TeamPlayerRecycleAdapter(
    private val context: Context,
    private val players: List<Player>,
    private val listener: (Player) -> Unit
) : RecyclerView.Adapter<TeamPlayerRecycleAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        return TeamPlayerRecycleAdapter.ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.team_players_item_row, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(players[position], listener)
    }

    class ViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bindData(players: Player, listener: (Player) -> Unit) {

            containerView.tvPlayerName.text = players.strPlayer
            containerView.tvPlayerPosition.text = players.strPosition

            Glide.with(containerView).load(players.strCutout).into(containerView.ivPlayerImg)

            containerView.setOnClickListener { listener(players) }

        }

    }

}