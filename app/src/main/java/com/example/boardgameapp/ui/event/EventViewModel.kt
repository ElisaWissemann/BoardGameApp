package com.example.boardgameapp.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.boardgameapp.repositories.BoardGameRepository
import com.example.boardgameapp.repositories.dto.GameNight

/*** EventViewModel - business logic for the EventScreen*/

class EventViewModel(private val repository: BoardGameRepository) : ViewModel() {

    /**
     * Retrieve a specific gameNight from the repository.
     */
    fun retrieveGameNight(eventId: Int, hostId: Int): LiveData<GameNight> {
        return repository.retriveGameNight(eventId, hostId).asLiveData()
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
