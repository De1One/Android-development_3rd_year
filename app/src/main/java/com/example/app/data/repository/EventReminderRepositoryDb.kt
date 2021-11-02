package com.example.app.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.*
import com.example.app.data.database.AppDatabase
import com.example.app.data.database.model.EventReminderDb
import com.example.app.data.mapper.mapToDb
import com.example.app.domain.model.EventsReminder
import com.example.app.domain.repository.EventReminderRepository

class EventReminderRepositoryDb(
    private val database: AppDatabase,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : EventReminderRepository {

    override suspend fun getEventReminderList(): Flow<List<EventsReminder>> = withContext(defaultDispatcher) {
        database.eventReminderDao().getAll()
            .map {
                return@map it.map {
                    EventsReminder(it.id, it.title, it.desc ?: "", it.dateStart)
                }
            }
    }
    override suspend fun getEventReminder(id: Int): EventsReminder? = withContext(defaultDispatcher) {
        val eventDb = database.eventReminderDao().getById(id).firstOrNull()

        eventDb?.run { EventsReminder(id, title, desc ?: "", dateStart) }
    }

    override suspend fun removeEventReminder(eventReminder: EventsReminder) = withContext(defaultDispatcher) {
        val eventDb = with(eventReminder) {
            EventReminderDb(id, title, desc, dateStart)
        }
        database.eventReminderDao().remove(eventDb)
    }

    override suspend fun saveEventReminder(eventReminder: EventsReminder) = withContext(defaultDispatcher){
        database.eventReminderDao().save(eventReminder.mapToDb())
    }
}