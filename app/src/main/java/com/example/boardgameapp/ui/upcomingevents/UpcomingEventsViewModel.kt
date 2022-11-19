package com.example.boardgameapp.ui.upcomingevents

import androidx.lifecycle.*
import com.example.boardgameapp.repositories.BoardGameRepository
import com.example.boardgameapp.db.entities.Event
import com.example.boardgameapp.db.entities.User
import com.example.boardgameapp.repositories.dto.UpcomingGameNight
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the Inventory repository and an up-to-date list of all items.
 *
 */
class UpcomingEventsViewModel(private val repository: BoardGameRepository) : ViewModel() {

    val upcomingGameNights: LiveData<List<UpcomingGameNight?>> = repository.getUpcomingEventsStream().asLiveData()

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class UpcomingEventsViewModelFactory(private val repository: BoardGameRepository) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingEventsViewModel::class.java)) {
            return UpcomingEventsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}