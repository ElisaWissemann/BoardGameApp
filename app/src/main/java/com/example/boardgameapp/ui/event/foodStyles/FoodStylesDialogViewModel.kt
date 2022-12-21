package com.example.boardgameapp.ui.event.foodStyles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.ui.event.attendence.AttendenceViewModel

class FoodStylesDialogViewModel(): ViewModel() { // TODO Bodo delete?

}


/**
 * ViewModelFactory for FoodStyles
 */
class AttendenceViewModelFactory(
    private val repository: BoardGameRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AttendenceViewModel::class.java)) {
            return AttendenceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
