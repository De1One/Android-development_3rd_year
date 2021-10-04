package com.example.app.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.model.EventsReminder
import java.util.*

class EventsViewModel : ViewModel() {
    private val eventsReminder: MutableLiveData<List<EventsReminder>> = MutableLiveData()

    init{
        loadEventsReminder()
    }
    fun getEvents() : LiveData<List<EventsReminder>>{
        return eventsReminder
    }

    private fun loadEventsReminder(){
        val list = listOf(
            EventsReminder("Events first","Descr first", Date()),
            EventsReminder("Events second","Descr second", Date()),
            EventsReminder("Events third","Descr third", Date()),
            EventsReminder("Events fourth","Descr fourth", Date()),
            EventsReminder("Events fifth","Descr fifth", Date())
        )
        eventsReminder.postValue(list)
    }
}