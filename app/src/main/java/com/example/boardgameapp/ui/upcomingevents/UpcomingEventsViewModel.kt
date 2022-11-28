package com.example.boardgameapp.ui.upcomingevents

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.boardgameapp.repositories.BoardGameRepository
import com.example.boardgameapp.database.dto.UpcomingGameNight

/**
 * View Model to keep a reference to the Inventory repository and an up-to-date list of all items.
 *
 */
class UpcomingEventsViewModel(private val repository: BoardGameRepository) : ViewModel() {

    val upcomingGameNights: LiveData<List<UpcomingGameNight?>> =
        repository.getUpcomingEvents().asLiveData()

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class UpcomingEventsViewModelFactory(private val repository: BoardGameRepository) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingEventsViewModel::class.java)) {
            return UpcomingEventsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}