package com.example.boardgameapp.ui.upcomingevents

import androidx.lifecycle.*
import com.example.boardgameapp.data.repositories.BoardGameRepository

class UpcomingEventsItemViewModel(private val repository: BoardGameRepository): ViewModel() {

    }

class UpcomingEventsItemViewModelFactory(private val repository: BoardGameRepository): ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UpcomingEventsItemViewModel::class.java)){
            return UpcomingEventsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}