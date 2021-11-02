package com.example.app.ui.common

import android.app.Application
import com.example.app.data.database.AppDatabase
import com.example.app.data.repository.EventReminderRepositoryDb
import com.example.app.domain.repository.EventReminderRepository
import com.example.app.ui.main.MainRouter

class App : Application() {

    private val appDatabase: AppDatabase by lazy { AppDatabase.getInstance(this) }
    lateinit var mainRouter: MainRouter

    val eventReminderRepository: EventReminderRepository by lazy {
        EventReminderRepositoryDb(appDatabase)
    }

    override fun onCreate() {
        super.onCreate()
    }
}