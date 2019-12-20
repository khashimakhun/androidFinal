package com.larapin.footballappkotlin.favorite.event

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.db.FavoriteEvent
import com.larapin.footballappkotlin.util.gone
import com.larapin.footballappkotlin.util.toGMTFormat
import kotlinx.android.synthetic.main.item_event.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat

/**
 * Created by Avin on 11/09/2018.
 * class FavoriteEventAdapter
 */
class FavoriteEventAdapter(private val context: Context, private val favorite: List<FavoriteEvent>, private val listener: (FavoriteEvent) -> Unit)
    : RecyclerView.Adapter<FavoriteEventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteEventViewHolder {
        return FavoriteEventViewHolder((LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteEventViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }
}

class FavoriteEventViewHolder(view: View) : RecyclerView.ViewHolder(view){
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun bindItem(favorite: FavoriteEvent, listener: (FavoriteEvent) -> Unit){
        if(favorite.scoreHome.isNullOrEmpty() && favorite.scoreAway.isNullOrEmpty()){
            itemView.tv_skor.text = "VS"
        }else{
            itemView.tv_skor.text = favorite.scoreHome+"   VS   "+favorite.scoreAway
        }

        if (favorite.eventDate.isNullOrEmpty() || favorite.eventTime.isNullOrEmpty()){
        }else{
            val tanggal = SimpleDateFormat("EEE, d MMM yyyy")
                    .format(toGMTFormat(favorite.eventDate, favorite.eventTime))
            itemView.tv_date.text = tanggal
            val waktu = SimpleDateFormat("HH:mm")
                    .format(toGMTFormat(favorite.eventDate, favorite.eventTime))
            itemView.tv_time.text = waktu
        }

        itemView.tv_home.text = favorite.teamHome
        itemView.tv_away.text = favorite.teamAway
        itemView.btn_alert.gone()
        itemView.onClick { listener(favorite) }
    }

}