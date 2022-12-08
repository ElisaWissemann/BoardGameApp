package com.example.boardgameapp.ui.event.suggestGame

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.boardgameapp.data.entities.EventGameCrossRef
import com.example.boardgameapp.data.repositories.BoardGameRepository

class SuggestGamesDialogViewModel (private val repository: BoardGameRepository): ViewModel() {

    val games: LiveData<Array<String>> = repository.getGamesArray().asLiveData()

    suspend fun updateEventWithSelectedGame(game: String, eventId: Int){
        val gameId = repository.getGameId(game)
        repository.addEventGameCrossRef(EventGameCrossRef(eventId, gameId, arrayListOf()))
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class SuggestGamesDialogViewModelFactory(private val repository: BoardGameRepository) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuggestGamesDialogViewModel::class.java)) {
            return SuggestGamesDialogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}