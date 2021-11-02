package com.example.app.domain.model

import java.util.*

data class EventsReminder(val id: Int?,val title: String,val desc: String,val dateStart: Date){
    companion object{
        fun emptyEventsReminder() = EventsReminder(null,"","",Date())
    }
}