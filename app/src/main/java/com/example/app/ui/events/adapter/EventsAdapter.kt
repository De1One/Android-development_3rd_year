package com.example.app.ui.events.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.EventItemBinding


internal class EventsAdapter : RecyclerView.Adapter<EventVH>() {

    private val eventReminderList = mutableListOf<EventReminderVM>()

    fun reload(newData: List<EventReminderVM>) {
        eventReminderList.clear()
        eventReminderList.addAll(newData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventVH {
        return EventVH(
            EventItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: EventVH, position: Int) {
        holder.bind(eventReminderList[position])
    }
    override fun getItemCount(): Int = eventReminderList.size
}
