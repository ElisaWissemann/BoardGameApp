package com.example.boardgameapp.ui.event

import android.util.Log
import androidx.lifecycle.*
import com.example.boardgameapp.db.entities.User
import com.example.boardgameapp.repositories.BoardGameRepository
import com.example.boardgameapp.repositories.dto.GameNight
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

/*** EventViewModel - business logic for the EventScreen*/

class EventViewModel(private val repository: BoardGameRepository) : ViewModel() {

    /**
     * Retrieve a specific gameNight from the repository.
     */
    fun retrieveGameNight(eventId: Int, hostId: Int): LiveData<GameNight> {
        return repository.retriveGameNight(eventId, hostId).asLiveData()
    }

    /**Creates a new Event object with the updated attendence information*/

    suspend fun buildEventWithUpdatedAttendence(){

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
    }

    /**
     * Launching a new coroutine to update an item in a non-blocking way
     */
    private suspend fun updateUser(user: User) {
            repository.updateUser(user)
    }

    /**
     * Called to update an existing entry in the Inventory database.
     * Returns an instance of the [Item] entity class with the item info updated by the user.
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
 * ViewModelFactory for EventScreen
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
