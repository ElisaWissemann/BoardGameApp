package com.example.boardgameapp.ui.event.foodStyles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.boardgameapp.data.entities.EventFoodCrossRef
import com.example.boardgameapp.data.repositories.BoardGameRepository

class FoodStylesDialogViewModel(private val repository: BoardGameRepository) : ViewModel() {

    val foodStyles: LiveData<Array<String>> = repository.getFoodStylesArray().asLiveData()

    /**Function that creates a new enty in the EventFoodCrossRef*/
    suspend fun updateEventWithSelectedFoodStyle(foodStyle: String, eventId: Int) {
        val foodStyleId = repository.getFoodStyleId(foodStyle)
        repository.addEventFoodCrossRef(EventFoodCrossRef(eventId, foodStyleId))
    }
}


/**
 * ViewModelFactory for FoodStyles
 */
class FoodStylesDialogViewModelFactory(
    private val repository: BoardGameRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodStylesDialogViewModel::class.java)) {
            return FoodStylesDialogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
