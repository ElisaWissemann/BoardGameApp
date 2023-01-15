package com.example.boardgameapp.ui.event

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.boardgameapp.data.BoardGameDao_Impl
import com.example.boardgameapp.data.dto.GameNight
import com.example.boardgameapp.data.entities.User
import com.example.boardgameapp.data.repositories.BoardGameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

/*** EventViewModel - business logic for the EventScreen*/

class EventViewModel(private val repository: BoardGameRepository) : ViewModel() {


    private var _eventId = MutableStateFlow(0)
    val eventId: StateFlow<Int> = _eventId.asStateFlow()


    /*---------------Event---------------------*/

    fun setEventId(eventId:Int){
        _eventId.value = eventId
    }

    /*---------------Game---------------------*/

    fun retrieveEventGameNames(eventId: Int): LiveData<List<String>> {
        return repository.getEventsSuggestedGameNames(eventId).asLiveData()
    }
    /**
     * Retrieve a specific gameNight from the repository.
     */
    fun retrieveGameNight(eventId: Int, hostId: Int): LiveData<GameNight> {
        return repository.retriveGameNight(eventId, hostId).asLiveData()
    }

    /*---------------FoodStyle---------------------*/

    suspend fun retrieveEventFoodNames(eventId: Int): StateFlow<List<String>> {
         val coroutineScope = CoroutineScope(Job())
        val result = repository.getEventSuggestedFoodNames(eventId).stateIn(scope = coroutineScope)
        return result
    }

    /*---------------User---------------------*/
    /**
     * Launching a new coroutine to update an item in a non-blocking way
     */
    private suspend fun updateUser(user: User) {
        repository.updateUser(user)
    }

     fun setEventID(eventId: Int ){
        _eventId.value = eventId
    }

    /**
     * Creates a new User object with the updated rating and hands it over to updateUser(user)
     */
    suspend fun buildUserWithNewRating(
        rating: Float, hostId: Int
    ) {
        repository.getUserStream(hostId).collect {
             updateUser(it.copy(rating = it.rating?.plus(rating) as ArrayList<Double>?))
        }
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class EventViewModelFactory(
    private val repository: BoardGameRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
            return EventViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
