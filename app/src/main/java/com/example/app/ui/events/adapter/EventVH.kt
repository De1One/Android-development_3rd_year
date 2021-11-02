package com.example.app.ui.events.adapter

import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.EventItemBinding
import java.text.SimpleDateFormat
import java.util.*

internal class EventVH(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy hh:mm", Locale.getDefault())
    fun bind(eventReminderVM: EventReminderVM) {
        val eventReminder = eventReminderVM.data
        binding.apply {
            setVariable(BR.viewModel,eventReminderVM)
            eventsItemDescription.visibility = if (eventReminder.desc.isBlank()) View.GONE else View.VISIBLE

            eventsItemDate.text = simpleDateFormat.format(eventReminder.dateStart)
        }
    }
}