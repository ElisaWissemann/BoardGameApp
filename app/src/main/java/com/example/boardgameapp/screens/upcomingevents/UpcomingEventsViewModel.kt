package com.example.boardgameapp.screens.upcomingevents

import android.util.Log
import androidx.lifecycle.*
import com.example.boardgameapp.database.BoardGameRepository
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.entities.User
import kotlinx.coroutines.launch

class UpcomingEventsViewModel(private val repository: BoardGameRepository) : ViewModel() {

    private var _eventData = MutableLiveData<MutableList<Event>>()
    val eventData: LiveData<MutableList<Event>>  get() = _eventData

    private var _hostData = MutableLiveData<List<User>>()
    val hostData: LiveData<List<User>>  get() = _hostData

    init{

        //_eventData.value = EventDataSource.events
        _eventData.value = getAllEvents()
        _hostData.value = getAllUsers()
        //_hostData.value =  UserDataSource.users

        Log.i("ELISA", getAllEvents().toString())
        Log.i("ELISA", _eventData.value.toString())
    }
        private fun insertEvent(event: Event) = viewModelScope.launch {
        repository.insertEvent(event)
    }
    //fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    private fun getAllEvents() = repository.events
    private fun getAllUsers() = repository.users



}