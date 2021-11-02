package com.example.app.ui.eventdescription

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.app.domain.model.EventsReminder
import com.example.app.ui.common.App
import kotlinx.coroutines.launch

class EventDescriptionVM(application: Application) : AndroidViewModel(application) {

    private val eventReminderData: MutableLiveData<EventsReminder> = MutableLiveData()

    fun getEventReminder(): LiveData<EventsReminder> = eventReminderData
    //TODO: Добавить переменную
    // val titleObs = ObservableField("")
    // var eventReminder: EventReminder? = null

    fun loadData(eventId: Int) {
        viewModelScope.launch {
            val event = getApplication<App>().eventReminderRepository.getEventReminder(eventId)
            eventReminderData.value = event
            //TODO Связать данную VM с fragment_event_desc

            //TODO Добавить в xml данные о деталях события и дату
            //TODO: Установить значения в переменные titleObs и eventReminder
        }
    }

    fun removeEvent() {
        viewModelScope.launch {
            eventReminderData.value?.let {
                getApplication<App>().eventReminderRepository.removeEventReminder(it)
            }
            closeView()
        }
    }

    fun editEvent() {
        getApplication<App>().mainRouter.showEventEditScreen(eventReminderData.value?.id ?: -1)
    }

    private fun closeView() {
        getApplication<App>().mainRouter.back()
    }
}