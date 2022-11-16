package com.example.boardgameapp.ui.upcomingevents

import androidx.lifecycle.*
import com.example.boardgameapp.db.BoardGameDao
import com.example.boardgameapp.repositories.BoardGameRepository
import com.example.boardgameapp.db.entities.Event
import com.example.boardgameapp.db.entities.User
import com.example.boardgameapp.repositories.dto.UpcomingGameNight
import kotlinx.coroutines.launch

class UpcomingEventsItemViewModel(private val repository: BoardGameRepository): ViewModel() {

    }

class UpcomingEventsItemViewModelFactory(private val repository: BoardGameRepository): ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //TODO Change to UpcomingEventsItemViewModel
        if(modelClass.isAssignableFrom(UpcomingEventsItemViewModel::class.java)){
            return UpcomingEventsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}