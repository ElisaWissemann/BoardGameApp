package com.example.boardgameapp.ui.event.delayed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boardgameapp.data.entities.LoggedInUser
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.ui.event.attendence.AttendenceViewModel

class DelayedViewModel (private val repository: BoardGameRepository) : ViewModel(){

     val loggedInUserId: Int = repository.loggedInUser().userId

    fun getUserName(userId: Int): String{
        return repository.getUserName(userId)
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */

class DelayedViewModelFactory(
    private val repository: BoardGameRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DelayedViewModel::class.java)) {
            return DelayedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
