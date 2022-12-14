package com.example.boardgameapp.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.boardgameapp.data.BoardGameDao_Impl
import com.example.boardgameapp.data.dto.GameNight
import com.example.boardgameapp.data.entities.User
import com.example.boardgameapp.data.repositories.BoardGameRepository
import kotlinx.coroutines.flow.*

/*** EventViewModel - business logic for the EventScreen*/

class EventViewModel(private val repository: BoardGameRepository) : ViewModel() {


    private var _eventId = MutableStateFlow(0)
    val eventId: StateFlow<Int> = _eventId.asStateFlow()

     fun retrieveEventGameNames(eventId: Int): LiveData<List<String>> {
        return repository.getEventsSuggestedGameNames(eventId).asLiveData()
    }
    /**
     * Retrieve a specific gameNight from the repository.
     */
    fun retrieveGameNight(eventId: Int, hostId: Int): LiveData<GameNight> {
        return repository.retriveGameNight(eventId, hostId).asLiveData()
    }

/*---------------Update User---------------------*/
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
        val currentUser = repository.getUserStream(hostId)
        var updatedItem: User
        currentUser.collect {
            updatedItem = getUpdatedItemEntry(
                it.id,
                it.name,
                it.surname!!,
                it.address!!,
                it.hosted_events!!,
                it.favorite_game,
                it.favorite_food,
                it.rating?.plus(rating) as ArrayList<Double>
                //addElementToArray(it.rating, rating.toDouble())
            )
            updateUser(updatedItem)
        }
        // TODO Bodo could be:
        //repository.getUserStream(hostId).collect {
        //    it.copy(rating = it.rating?.plus(rating) as ArrayList<Double>?)
        //}
    }

    /**
     * Called to update an existing entry in the BoardGame database.
     * Returns an instance of the [User] entity class with the user info updated with rating by the user.
     */
    private fun getUpdatedItemEntry(
        userId: Int,
        userName: String,
        userSurname: String,
        userAddress: String,
        userHosted_events: ArrayList<Int>?,
        userFavorite_game: String?,
        userFavorite_food: String?,
        userRating: ArrayList<Double>
    ): User {
        return User(
            id = userId,
            name = userName,
            surname = userSurname,
            address = userAddress,
            hosted_events = userHosted_events,
            favorite_game = userFavorite_game,
            favorite_food = userFavorite_food,
            rating = userRating
        )
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
