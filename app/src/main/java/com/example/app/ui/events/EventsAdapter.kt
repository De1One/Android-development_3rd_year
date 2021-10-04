package com.example.app.ui.events;

import android.util.EventLogTags
import android.view.LayoutInflater
import android.view.View;
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.databinding.FragmentEventsBinding
import com.example.app.model.EventsReminder


class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>(){

    private var eventsList = listOf<EventsReminder>()

    fun reload(events: List<EventsReminder>){
        eventsList = events
        notifyItemRangeChanged(0,events.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventsViewHolder(inflater.inflate(R.layout.event_item,parent,false))
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(eventsList[position])
    }

    override fun getItemCount(): Int = eventsList.size

    class EventsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val titleView : TextView by lazy { itemView.findViewById(R.id.eventsItem_title) }
        private val descriptionView: TextView by lazy{ itemView.findViewById(R.id.eventsItem_description)}
        private val dateView : TextView by lazy{itemView.findViewById(R.id.eventsItem_date)}

        fun bind(event : EventsReminder){
            titleView.text = event.title
            descriptionView.text = event.desc
            dateView.text = event.dateStart.toString()
        }
    }
}
