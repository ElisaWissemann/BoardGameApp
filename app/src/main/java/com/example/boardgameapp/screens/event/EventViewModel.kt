package com.example.boardgameapp.screens.event

import android.util.Log
import androidx.lifecycle.*
import com.example.boardgameapp.data.BoardGameRepository
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.game.Game
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.data.user.UserDataSource
import kotlinx.coroutines.launch

/**
 * EventViewModel - business logic for the EventScreen
 * it should hold all the data for the EventScreen*/
//TODO: STOPPED @ try to fix ViewModelFactory Issue -> compare to SleepTrackerViewModel!

class EventViewModel( private val repository: BoardGameRepository, private val eventId: Int) : ViewModel() {

    // Data encapsulation, variables with _ are Mutable and private result will be passed to variable
    // without an _ to ensure that the value can't be changed from outside
    /*Event*/

    //hand over to layoutRessource
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date


    /*Host*/
    //local
    private var hostData = listOf<User>()

    //hand over to layoutRessource
    private val _hostId = MutableLiveData<Int>()
    val hostId: LiveData<Int> get() = _hostId

    private val _host = MutableLiveData<User>()
    val host: LiveData<User> get() = _host

    private val _hostName = MutableLiveData<String>()
    val hostName: LiveData<String> get() = _hostName


    //Initialize Block - gets called when the ViewModel is created
    init {
        /*Event*/
        var eventData = EventDataSource.events
        var event = eventData.find { it.id == eventId }!!
        _date.value = event.date
        /*Host*/
        hostData = UserDataSource.users
        _host.value = hostData.find { it.id == event.host }!!
        _hostId.value = _host.value?.id
        _hostName.value = "Host: " + _host.value?.name + " " + _host.value?.surname

    }

    private fun insertGame(game: Game) = viewModelScope.launch {
        repository.insert(game)
    }
}