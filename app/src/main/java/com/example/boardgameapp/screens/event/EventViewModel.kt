package com.example.boardgameapp.screens.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.boardgameapp.database.BoardGameRepository
import com.example.boardgameapp.database.entities.User

/**
 * EventViewModel - business logic for the EventScreen
 * it should hold all the data for the EventScreen*/
//TODO: STOPPED @ try to fix ViewModelFactory Issue -> compare to SleepTrackerViewModel!

class EventViewModel(private val repository: BoardGameRepository, private val eventId: Int) :
    ViewModel() {

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

    private val _hostRating = MutableLiveData<ArrayList<Double>?>()
    val hostRating: LiveData<ArrayList<Double>?> get() = _hostRating

    //Initialize Block - gets called when the ViewModel is created
    init {
        /*Event*/
        var eventData = getAllEvents()
        var event = eventData.find { it.id == eventId }!!
        _date.value = event.date
        /*Host*/
        hostData = getAllUsers()
        _host.value = hostData.find { it.id == event.host }!!
        _hostId.value = _host.value?.id
        _hostName.value = "Host: " + _host.value?.name + " " + _host.value?.surname
        _hostRating.value = _host.value!!.rating


    }

    private fun getAllEvents() = repository.events
    private fun getAllUsers() = repository.users
}