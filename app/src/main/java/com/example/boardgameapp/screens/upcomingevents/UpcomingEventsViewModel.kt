package com.example.boardgameapp.screens.upcomingevents

import android.util.Log
import androidx.lifecycle.*
import androidx.test.core.app.ActivityScenario.launch
import com.example.boardgameapp.data.BoardGameRepository
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.game.Game
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.data.user.UserDataSource
import kotlinx.coroutines.launch

class UpcomingEventsViewModel(private val repository: BoardGameRepository) : ViewModel() {

    private var _eventData = MutableLiveData<MutableList<Event>>()
    val eventData: LiveData<MutableList<Event>>  get() = _eventData

    private var _hostData = MutableLiveData<List<User>>()
    val hostData: LiveData<List<User>>  get() = _hostData

    init{
        _eventData.value = EventDataSource.events
        _hostData.value =  UserDataSource.users

        insertEvent(Event(0, 1,"22.10.23", arrayListOf(1,2), arrayListOf(3)))
        insertEvent(Event(0, 1,"22.10.23", arrayListOf(1,2), arrayListOf(3)))
    }
        private fun insertEvent(event:Event) = viewModelScope.launch {
        repository.insertEvent(event)
    }



}