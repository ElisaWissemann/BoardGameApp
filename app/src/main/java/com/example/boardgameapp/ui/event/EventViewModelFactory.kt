package com.example.boardgameapp.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boardgameapp.model.repositories.BoardGameRepository

class EventViewModelFactory (private val repository: BoardGameRepository, private val eventId: Int): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EventViewModel::class.java)){
            return EventViewModel(repository, eventId) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
