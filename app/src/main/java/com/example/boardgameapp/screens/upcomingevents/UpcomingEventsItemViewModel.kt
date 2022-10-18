package com.example.boardgameapp.screens.upcomingevents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource

class UpcomingEventsItemViewModel: ViewModel() {

    private var _eventData = MutableLiveData<List<Event>>()
    val eventData: LiveData<List<Event>>  get() = _eventData

    init{
        _eventData.value = EventDataSource.events
    }
}