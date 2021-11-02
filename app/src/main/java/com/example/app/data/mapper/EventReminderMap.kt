package com.example.app.data.mapper

import com.example.app.data.database.model.EventReminderDb
import com.example.app.domain.model.EventsReminder

fun EventsReminder.mapToDb() = with(this) {
    EventReminderDb(id, title, desc, dateStart)
}