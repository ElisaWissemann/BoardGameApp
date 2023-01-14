package com.example.boardgameapp.ui.event.attendence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boardgameapp.data.entities.Event
import com.example.boardgameapp.data.entities.LoggedInUser
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.data.usecases.UpdateEventWithAttendenceUseCase

class AttendenceViewModel(private val repository: BoardGameRepository) : ViewModel() {

    private var _accepted: MutableLiveData<ArrayList<String>> =
        MutableLiveData<ArrayList<String>>(arrayListOf())
    val accepted: LiveData<ArrayList<String>> get() = _accepted

    private var _cancelled: MutableLiveData<ArrayList<String>> =
        MutableLiveData<ArrayList<String>>(arrayListOf())
    val cancelled: LiveData<ArrayList<String>> get() = _cancelled

    private var loggedInUserId: Int = repository.loggedInUser().userId


    suspend fun updatedEventWithAttendence(flag: Int, eventId:Int){
       UpdateEventWithAttendenceUseCase(repository, loggedInUserId).updatedEventWithAttendence(flag,eventId)
   }
    /**Function to setAttendies*/
    suspend fun setAttendies(eventId: Int) {
        val event = repository.getEventStream(eventId)
        event.collect {
            val acceptedIds = it.accepted
            val cancelledIds = it.cancelled
            val tempAcceptedArray: ArrayList<String> = arrayListOf()
            val tempCancelledArray: ArrayList<String> = arrayListOf()

            acceptedIds?.forEach { id ->
                tempAcceptedArray.add(repository.getUserName(id))
            }
            _accepted.value = tempAcceptedArray

            cancelledIds?.forEach { id ->
                tempCancelledArray.add(repository.getUserName(id))
            }
            _cancelled.value = tempCancelledArray
        }
    }


}

 /**
 * Factory class to instantiate the [ViewModel] instance.
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
