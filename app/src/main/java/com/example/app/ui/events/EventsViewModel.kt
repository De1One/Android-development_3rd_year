package com.example.app.ui.events

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app.domain.model.EventsReminder
import com.example.app.ui.common.App
import com.example.app.ui.events.adapter.EventReminderVM
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventsViewModel(application: Application) : AndroidViewModel(application) {

    private val eventsReminderVM: MutableLiveData<List<EventReminderVM>> = MutableLiveData()

    init {
        loadEventsReminder()
    }

    fun getEvents(): LiveData<List<EventReminderVM>> {
        return eventsReminderVM
    }

    private fun clickEventReminder(eventReminder: EventsReminder) {
        eventReminder.id?.let {
            getApplication<App>().mainRouter.showEventDescriptionScreen(it)
        }
    }

    private fun EventsReminder.mapToEventReminderVM() = EventReminderVM(this) {
        clickEventReminder(this)
    }

    private fun loadEventsReminder() {
        viewModelScope.launch {
            getApplication<App>().eventReminderRepository
                .getEventReminderList()
                .collect {
                    eventsReminderVM.value = it.map { it.mapToEventReminderVM() }
                }
        }
    }

    fun showAddNewEventScreen() {
        getApplication<App>().mainRouter.showEventEditScreen()
    }
}