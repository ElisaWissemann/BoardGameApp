package com.example.boardgameapp.screens.upcomingevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boardgameapp.data.BoardGameRepository

class UpcomingEventViewModelFactory (private val repository: BoardGameRepository): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UpcomingEventsViewModel::class.java)){
            return UpcomingEventsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}