package com.example.boardgameapp.screens.upcomingevents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardgameapp.data.BoardGameRepository
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.game.Game
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.data.user.UserDataSource
import kotlinx.coroutines.launch

class UpcomingEventsViewModel(private val repository: BoardGameRepository) : ViewModel() {

    private var _eventData = MutableLiveData<List<Event>>()
    val eventData: LiveData<List<Event>>  get() = _eventData

    private var _hostData = MutableLiveData<List<User>>()
    val hostData: LiveData<List<User>>  get() = _hostData

    init{
        _eventData.value = EventDataSource.events
        _hostData.value =  UserDataSource.users

        insertEvent(Event(0, 1,"25.01.2022", arrayListOf("Anna", "Frank")))
    }
        private fun insertEvent(event:Event) = viewModelScope.launch {
        repository.insertEvent(event)
    }
}