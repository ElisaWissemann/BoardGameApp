package com.example.boardgameapp.ui.event.attendence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boardgameapp.data.entities.Event
import com.example.boardgameapp.data.entities.LoggedInUser
import com.example.boardgameapp.data.repositories.BoardGameRepository

class AttendenceViewModel(private val repository: BoardGameRepository) : ViewModel() {

    private var _accepted: MutableLiveData<ArrayList<String>> =
        MutableLiveData<ArrayList<String>>(arrayListOf())
    val accepted: LiveData<ArrayList<String>> get() = _accepted

    private var _cancelled: MutableLiveData<ArrayList<String>> =
        MutableLiveData<ArrayList<String>>(arrayListOf())
    val cancelled: LiveData<ArrayList<String>> get() = _cancelled

    private var loggedInUser: LoggedInUser = repository.loggedInUser()


    /*---------------Update Event---------------------*/

    /**
     * Launching a new coroutine to update an item in a non-blocking way
     */
    private suspend fun updateEvent(event: Event) {
        repository.updateEvent(event)
    }

    //TODO: Check if id is already in DB & check if it is already in the other filed e.g. if user accepted the event if his id was already in cancelled and if so do delete it from there an update the new filed
    /**Creates a new Event object with the updated attendence information*/
    suspend fun updatedEventWithAttendence(flag: Int, eventId: Int) {
        val currentEvent = repository.getEventStream(eventId)
        var updatedItem: Event
        when (flag) {

            //check if this user already cancelled or accepted the event prior
            // if he did he will be deleted from the db before his new descision is saved to the DB
            // 0 = accepted, 1/else = cancelled

            //accepted
            0 -> {
                currentEvent.collect {
                    val newEntry =
                        checkUserAlreadyAcceptedOrCancelledAndRemove(it, loggedInUser.userId, 0)
                    updatedItem =
                        getUpdatedEventEntry(
                            it.id,
                            it.host,
                            it.date,
                            newEntry.accepted,
                            newEntry.cancelled
                        )
                    updateEvent(updatedItem)
                }
            }
            else -> {
                currentEvent.collect {
                    val newEntry =
                        checkUserAlreadyAcceptedOrCancelledAndRemove(it, loggedInUser.userId, 1)
                    updatedItem =
                        getUpdatedEventEntry(
                            it.id,
                            it.host,
                            it.date,
                            newEntry.accepted,
                            newEntry.cancelled
                        )
                  updateEvent(updatedItem)
                }
            }
        }
    }

    /**Function to create new event Object that holds new user attendence*/
    private fun checkUserAlreadyAcceptedOrCancelledAndRemove(
        event: Event,
        id: Int,
        flag: Int
    ): Event {
        when (flag) {
            // 0 = accepted, 1/else = cancelled
            //user accepted event
            0 -> {
                //if the user already accepted or cancelled the event, his id will be found in the DB
                //if he already accepted the event prior nothing else will happen
                // if his id is not in the db yet, it will be set here
                if (event.accepted?.contains(id) != true) {
                    event.accepted?.add(id)
                }
                // if the user already cancelled the event before id will be deleted in the DB
                event.cancelled?.remove(id)
                return event
            }
            //user cancelled event
            else -> {
                //if the user already accepted the event, his id will be found in the DB
                if (event.cancelled?.contains(id) != true) {
                    //if so he will be removed from the given List
                    event.cancelled?.add(id)
                }
                // if the user already accepted the event id will be deleted in the DB
                event.accepted?.remove(id)
                return event
            }
        }
    }

    /**
     * Called to update an existing entry in the BoardGame database.
     * Returns an instance of the [Event] entity class with the accepted, or cancelled info updated by the user.
     */
    private fun getUpdatedEventEntry(
        id: Int,
        host: Int,
        date: String,
        accepted: ArrayList<Int>?,
        cancelled: ArrayList<Int>?,
    ): Event {
        return Event(
            id = id,
            host = host,
            date = date,
            accepted = accepted,
            cancelled = cancelled
        )
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
