package com.example.app.domain.repository

import com.example.app.domain.model.EventsReminder
import kotlinx.coroutines.flow.Flow

interface EventReminderRepository {

    suspend fun getEventReminderList(): Flow<List<EventsReminder>>

    suspend fun getEventReminder(id: Int): EventsReminder?

    suspend fun removeEventReminder(eventReminder: EventsReminder)

    suspend fun saveEventReminder(eventReminder: EventsReminder)
}