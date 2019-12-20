package com.larapin.footballappkotlin.main.event

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.model.event.Event
import com.larapin.footballappkotlin.util.invisible
import com.larapin.footballappkotlin.util.toGMTFormat
import com.larapin.footballappkotlin.util.visible
import kotlinx.android.synthetic.main.item_event.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat

/**
 * Created by Avin on 04/09/2018.
 * class EventAdapter
 */
class EventAdapter(private val context: Context, private val events: List<Event>, private val listener: (Event) -> Unit)
    : RecyclerView.Adapter<EventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

}

class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun bindItem(events: Event, listener: (Event) -> Unit){
        if(events.scoreHome.isNullOrEmpty() && events.scoreAway.isNullOrEmpty()){
            itemView.tv_skor.text = "VS"
            itemView.btn_alert.visible()
        }else{
            itemView.tv_skor.text = events.scoreHome+" VS "+events.scoreAway
            itemView.btn_alert.invisible()
        }

        if(events.eventDate.isNullOrEmpty() || events.eventTime.isNullOrEmpty()){
        }else{
            val tanggal = SimpleDateFormat("EEE, d MMM yyyy")
                    .format(toGMTFormat(events.eventDate, events.eventTime))
            itemView.tv_date.text = tanggal
            val waktu = SimpleDateFormat("HH:mm")
                    .format(toGMTFormat(events.eventDate, events.eventTime))
            itemView.tv_time.text = waktu
        }

        itemView.tv_home.text = events.teamHome
        itemView.tv_away.text = events.teamAway

        itemView.btn_alert.setOnClickListener {
            val intent = Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, events.eventDate?.let { it1 ->
                        events.eventTime?.let { it2 -> toGMTFormat(it1, it2)?.time }
                    })
                    .putExtra(CalendarContract.Events.TITLE, events.teamHome + " vs " + events.teamAway)
            startActivity(itemView.context, intent, null)
        }
        
        itemView.onClick { listener(events) }
    }

}
