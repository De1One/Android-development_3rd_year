package com.example.app.data.database.dao

import androidx.room.*

import com.example.app.data.database.helpers.TABLE_EVENT_REMINDER
import com.example.app.data.database.model.EventReminderDb
import kotlinx.coroutines.flow.Flow

@Dao
interface EventReminderDao {

    @Query("SELECT * FROM $TABLE_EVENT_REMINDER")
    fun getAll(): Flow<List<EventReminderDb>>

    @Query("SELECT * FROM $TABLE_EVENT_REMINDER WHERE id == :id")
    fun getById(id: Int): List<EventReminderDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<EventReminderDb>)

    @Delete
    fun remove(eventReminderDb: EventReminderDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(eventReminderDb: EventReminderDb)
}