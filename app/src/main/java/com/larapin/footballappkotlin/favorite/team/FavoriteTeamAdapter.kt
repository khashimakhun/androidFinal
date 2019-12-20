package com.larapin.footballappkotlin.favorite.team

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.db.FavoriteTeam
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Avin on 18/10/2018.
 * class FavoriteTeamAdapter
 */
class FavoriteTeamAdapter(private val context: Context, private val favorite: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<FavTeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamViewHolder {
        return FavTeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))
    }

    override fun onBindViewHolder(holder: FavTeamViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size
}

class FavTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(favorite: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        itemView.tv_team.text = favorite.teamName
        Picasso.with(itemView.context).load(favorite.teamBadge).into(itemView.img_team)
        itemView.onClick { listener(favorite) }
    }
}