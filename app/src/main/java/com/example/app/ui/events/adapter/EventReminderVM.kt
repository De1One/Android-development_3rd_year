package com.example.app.ui.events.adapter

import com.example.app.domain.model.EventsReminder

data class EventReminderVM(
    val data: EventsReminder,
    val onClick: () -> Unit
)