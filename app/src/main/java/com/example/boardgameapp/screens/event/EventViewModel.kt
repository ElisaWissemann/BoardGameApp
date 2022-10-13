package com.example.boardgameapp.screens.event


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.event.EventDataSource
import com.example.boardgameapp.data.user.User
import com.example.boardgameapp.data.user.UserDataSource

/**
 * EventViewModel - business logic for the EventScreen
 * it should hold all the data for the EventScreen*/
//insert DAO check Tutorial https://www.youtube.com/watch?v=qI1RDiCmMbo

class EventViewModel(private val state: SavedStateHandle) : ViewModel() {

    // Data encapsulation, variables with _ are Mutable and private result will be passed to variable
    // without an _ to ensure that the value can't be changed from outside
    /*Event*/
    //local
    private val _eventData = MutableLiveData<List<Event>>()
    private var _eventId: Int? = null
    private val _event = MutableLiveData<Event>()

    //hand over to layoutRessource
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date


    /*Host*/
    //local
    private val _hostData = MutableLiveData<List<User>>()

    private val _host = MutableLiveData<User>()
    val host: LiveData<User> get() = _host

    //hand over to layoutRessource
    private val _hostName = MutableLiveData<String>()
    val hostName: LiveData<String> get() = _hostName

    private val _hostId = MutableLiveData<Int>()
    val hostId: LiveData<Int> get() = _hostId


    //Initialize Block - gets called when the ViewModel is created
    init {
        //TODO: Make sure _eventData and _hostData will never be null --> exception handling required
        // Think about writing a loadEventData and loadHostData function
        /*Event*/
        _eventData.value = EventDataSource.events
        _eventId = state.get<Int>("eventId")
        _event.value = _eventData.value?.find { it.id == _eventId }
        _date.value = _event.value?.date
        /*Host*/
        _hostData.value = UserDataSource.users
        _host.value = _hostData.value?.find { it.id == _event.value?.host }
        _hostId.value = _host.value?.id
        _hostName.value = "Host: " + _host.value?.name + " " + _host.value?.surname

    }
}