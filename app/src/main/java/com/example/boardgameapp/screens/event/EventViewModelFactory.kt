package com.example.boardgameapp.screens.event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boardgameapp.data.BoardGameRepository

class EventViewModelFactory (private val repository: BoardGameRepository): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EventViewModel::class.java)){
            Log.i("ELISA", "ViewModelFactory created")
            return EventViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
