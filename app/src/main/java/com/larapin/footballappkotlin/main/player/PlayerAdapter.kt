package com.larapin.footballappkotlin.main.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.model.player.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Avin on 18/10/2018.
 * class PlayerAdapter
 */
class PlayerAdapter(private val context: Context, private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_player, parent, false))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }
}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    fun bindItem(players: Player, listener: (Player) -> Unit){
        Picasso.with(itemView.context).load(players.playerPic)
                .placeholder(R.drawable.progress_animation).into(itemView.img_player)
        itemView.tv_player.text = players.playerName
        itemView.tv_pos.text = players.playerPos
        itemView.onClick { listener(players) }
    }
}