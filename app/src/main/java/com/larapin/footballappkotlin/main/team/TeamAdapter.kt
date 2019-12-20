package com.larapin.footballappkotlin.main.team

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.model.team.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Avin on 18/10/2018.
 * class TeamAdapter
 */

class TeamAdapter(private val context: Context, private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder{
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(teams: Team, listener: (Team) -> Unit){
        itemView.tv_team.text = teams.teamName
        Picasso.with(itemView.context).load(teams.teamLogo)
                .placeholder(R.drawable.progress_animation).into(itemView.img_team)
        itemView.onClick { listener(teams) }
    }

}