package com.example.boardgameapp.screens.upcomingevents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boardgameapp.database.BoardGameDao
import com.example.boardgameapp.database.BoardGameRepository
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.entities.User

class UpcomingEventsItemViewModel(private val dao:BoardGameDao): ViewModel() {

    private var _eventData = MutableLiveData<List<Event>>()
    val eventData: LiveData<List<Event>>  get() = _eventData

    private var _hostData = MutableLiveData<List<User>>()
    val hostData: LiveData<List<User>>  get() = _hostData

    var repository = BoardGameRepository(dao)

    init{

      //  _eventData.value = getAllEvents()
        _hostData.value =  getAllUsers()
    }

    //private fun getAllEvents() = repository.events
    private fun getAllUsers() = repository.users

}

class UpcomingEventsItemViewModelFactory(private val dao: BoardGameDao): ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //TODO Change to UpcomingEventsItemViewModel
        if(modelClass.isAssignableFrom(UpcomingEventsViewModel::class.java)){
            return UpcomingEventsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}