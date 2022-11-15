package com.example.boardgameapp.ui.upcomingevents

import androidx.lifecycle.*
import com.example.boardgameapp.database.BoardGameDao
import com.example.boardgameapp.model.repositories.BoardGameRepository
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.entities.User
import com.example.boardgameapp.model.data.UpcomingGameNight
import kotlinx.coroutines.launch

class UpcomingEventsItemViewModel(private val dao:BoardGameDao): ViewModel() {

    var repository = BoardGameRepository(dao)

    private var _eventData = MutableLiveData<List<Event>>()
    val eventData: LiveData<List<Event>>  get() = _eventData

    private var _hostData = MutableLiveData<List<User>>()
    val hostData: LiveData<List<User>>  get() = _hostData

    val upcomingGameNights: LiveData<List<UpcomingGameNight?>> = repository.getUpcomingEventFlow().asLiveData()


    init{

      //  _eventData.value = getAllEvents()
//        _hostData.value =  getAllUsers()
    }

    //private fun getAllEvents() = repository.events
    private fun getAllUsers() = repository.getAllUsersNoFlow()
    private fun getUpcomingEvents() = viewModelScope.launch {
        repository.getUpcomingEventFlow()
    }

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