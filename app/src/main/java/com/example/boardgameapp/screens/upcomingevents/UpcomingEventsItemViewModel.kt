package com.example.boardgameapp.screens.upcomingevents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.boardgameapp.database.BoardGameRepository
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.entities.User
import com.example.boardgameapp.database.user.UserDataSource

class UpcomingEventsItemViewModel(private val repository: BoardGameRepository): ViewModel() {

    private var _eventData = MutableLiveData<List<Event>>()
    val eventData: LiveData<List<Event>>  get() = _eventData

    private var _hostData = MutableLiveData<List<User>>()
    val hostData: LiveData<List<User>>  get() = _hostData

    init{

        _eventData.value = getAllEvents()
        _hostData.value =  getAllUsers()
    }

    private fun getAllEvents() = repository.events
    private fun getAllUsers() = repository.users

}