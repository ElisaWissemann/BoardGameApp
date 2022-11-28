package com.example.boardgameapp.ui.choosegames

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.boardgameapp.data.repositories.BoardGameRepository

class ChooseGamesViewModel(private val repository: BoardGameRepository) : ViewModel() {

    val games: LiveData<Array<String>> = repository.getGamesArray().asLiveData()

}


/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class ChooseGamesViewModelFactory(private val repository: BoardGameRepository) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseGamesViewModel::class.java)) {
            return ChooseGamesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}