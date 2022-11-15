package com.example.boardgameapp.ui.upcomingevents

import androidx.lifecycle.*
import com.example.boardgameapp.database.BoardGameDao
import com.example.boardgameapp.model.repositories.BoardGameRepository
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.entities.User
import com.example.boardgameapp.model.data.UpcomingGameNight
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the Inventory repository and an up-to-date list of all items.
 *
 */
class UpcomingEventsViewModel(private val dao: BoardGameDao) : ViewModel() {

    var repository = BoardGameRepository(dao)

    // Cache all items form the database using LiveData
    val eventData: LiveData<List<Event>> = repository.getAllEvents().asLiveData()
    val hostData: LiveData<List<User>> = repository.getAllUsers().asLiveData()

    val upcomingGameNights: LiveData<List<UpcomingGameNight?>> = repository.getUpcomingEventFlow().asLiveData()




//    //TODO: Like above
//    private var _hostData = MutableLiveData<List<User>>()
//    val hostData: LiveData<List<User>> get() = _hostData

    private var _currentRating = MutableLiveData<List<User>>()
    val currentRating: LiveData<List<User>> get() = _currentRating

    init {


    }
    /*-----Event-----*/
    /**Inserts an Event*/
    private fun addNewEvent(event: Event) = viewModelScope.launch {
        repository.insertEvent(event)
    }

    private fun getUpcomingEvents() = viewModelScope.launch {
        repository.getUpcomingEventFlow()
    }

    // private fun getAllEvents() = repository.events

    /*-----Users/Hosts-----*/
    ///** Get All Users*/
    //private fun getAllUsers() = repository.users

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class UpcomingEventsViewModelFactory(private val dao: BoardGameDao) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingEventsViewModel::class.java)) {
            return UpcomingEventsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}