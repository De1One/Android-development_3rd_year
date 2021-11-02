package com.example.app.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.app.data.database.AppDatabase
import com.example.app.data.database.model.EventReminderDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class InitialDataDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val list = listOf(
            EventReminderDb(1, "День рождение ЕГО", "Нужно поздравить с утра", Date()),
            EventReminderDb(2, "День дня", "? ", Date()),
            EventReminderDb(3, "День группы", "", Date()),
        )

        try {
            val database = AppDatabase.getInstance(applicationContext)
            database.eventReminderDao().insertAll(list)
            Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}