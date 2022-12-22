package com.example.boardgameapp.ui.foodstyles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.boardgameapp.data.repositories.BoardGameRepository

class FoodStylesViewModel (private val repository: BoardGameRepository)  : ViewModel() {
    // TODO: Implement the ViewModel

    val foodStyles: LiveData<Array<String>> = repository.getFoodStylesArray().asLiveData()

}
/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class FoodStylesViewModelFactory(private val repository: BoardGameRepository) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodStylesViewModel::class.java)) {
            return FoodStylesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}