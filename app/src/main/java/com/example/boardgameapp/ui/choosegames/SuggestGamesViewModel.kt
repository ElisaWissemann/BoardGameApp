package com.example.boardgameapp.ui.choosegames

import androidx.lifecycle.*
import com.example.boardgameapp.data.entities.EventGameCrossRef
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.data.repositories.EventGameCrossRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuggestGamesViewModel(private val repository: EventGameCrossRepository) : ViewModel() {

    val games: LiveData<Array<String>> = repository.getGamesArray().asLiveData()

    suspend fun updateEventWithSelectedGame(game: String, eventId: Int){
        //TODO: get ID of the selected Game and Save it in the DB
        val gameId = repository.getGameId(game)

         repository.addEventGameCrossRef(EventGameCrossRef(eventId, gameId, arrayListOf()))

    }

}


/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class SuggestGamesViewModelFactory(private val repository: EventGameCrossRepository) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuggestGamesViewModel::class.java)) {
            return SuggestGamesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}