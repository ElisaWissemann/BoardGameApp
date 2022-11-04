package com.example.boardgameapp.screens.upcomingevents

import android.util.Log
import androidx.lifecycle.*
import com.example.boardgameapp.database.BoardGameDao
import com.example.boardgameapp.database.BoardGameRepository
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.entities.User
import kotlinx.coroutines.launch

class UpcomingEventsViewModel(private val dao : BoardGameDao) : ViewModel() {

    //private var _eventData = MutableLiveData<MutableList<Event>>()
    //val eventData: LiveData<MutableList<Event>>  get() = _eventData
    var repository = BoardGameRepository(dao)
    val eventData: LiveData<List<Event>> = repository.getAllEvents().asLiveData()

    private var _hostData = MutableLiveData<List<User>>()
    val hostData: LiveData<List<User>>  get() = _hostData

    init{

        //_eventData.value = EventDataSource.events
        //_eventData.value = getAllEvents()
        _hostData.value = getAllUsers()
        //_hostData.value =  UserDataSource.users

//        Log.i("ELISA", getAllEvents().toString())
//        Log.i("ELISA", _eventData.value.toString())
   }
//
//    /**Retrieve all Events from repository*/
//    fun getAllEvents():LiveData<List<Event>>{
//        return repository.getAllEvents().asLiveData()
//    }

    /**Inserts an Event*/
        private fun insertEvent(event: Event) = viewModelScope.launch {
        repository.insertEvent(event)
    }
   // private fun getAllEvents() = repository.events
    private fun getAllUsers() = repository.users


}
/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class UpcomingEventsViewModelFactory(private val dao:BoardGameDao): ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UpcomingEventsViewModel::class.java)){
            return UpcomingEventsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}