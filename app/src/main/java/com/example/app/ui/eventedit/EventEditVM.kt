package com.example.app.ui.eventedit

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.model.EventsReminder
import com.example.app.ui.common.App
import kotlinx.coroutines.launch
import java.util.*

class EventEditVM(application: Application) : AndroidViewModel(application) {

    val titleObs = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(value)
            eventReminder = eventReminder.copy(title = value!!)
        }
    }

    val descObs = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(value)
            eventReminder = eventReminder.copy(desc = value!!)
        }
    }
    val dateStringObs = object : ObservableField<Date>() {
        override fun set(value: Date?) {
            super.set(value)
            eventReminder = eventReminder.copy(dateStart = value!!)
        }
    }
    private lateinit var eventReminder: EventsReminder

    fun loadData(eventId: Int) {
        viewModelScope.launch {
            val event = getApplication<App>().eventReminderRepository.getEventReminder(eventId)
            eventReminder = event ?: EventsReminder.emptyEventsReminder()
            eventReminder.run {
                titleObs.set(title)
                descObs.set(desc)
                dateStringObs.set(dateStart)
            }
        }
    }

    fun saveData() {
        viewModelScope.launch {
            getApplication<App>().eventReminderRepository.saveEventReminder(eventReminder)
            closeView()
        }
    }

    private fun closeView() {
        getApplication<App>().mainRouter.back()
    }
}