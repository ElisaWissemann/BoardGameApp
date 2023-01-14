package com.example.boardgameapp.data.usecases

import com.example.boardgameapp.data.entities.Event
import com.example.boardgameapp.data.entities.LoggedInUser
import com.example.boardgameapp.data.repositories.BoardGameRepository

class UpdateEventWithAttendenceUseCase(var repository: BoardGameRepository, var loggedInUserId: Int) {
    /*---------------Update Event---------------------*/

    /**
     *  update an item in a non-blocking way
     */
    private suspend fun updateEvent(event: Event) {
        repository.updateEvent(event)
    }

    //TODO: Check if id is already in DB & check if it is already in the other filed e.g. if user accepted the event if his id was already in cancelled and if so do delete it from there an update the new filed
    /**
     * Creates a new Event object with the updated attendence information
     **/
    suspend fun updatedEventWithAttendence(flag: Int, eventId: Int) {
        val currentEvent = repository.getEventStream(eventId)
        var updatedItem: Event
        when (flag) {

            //check if this user already cancelled or accepted the event prior
            // if he did he will be deleted from the db before his new decision is saved to the DB
            // 0 = accepted, 1/else = cancelled

            //accepted
            0 -> {
                currentEvent.collect {
                    val newEntry =
                        checkUserAlreadyAcceptedOrCancelledAndRemove(it, loggedInUserId, 0)
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
                        checkUserAlreadyAcceptedOrCancelledAndRemove(it, loggedInUserId, 1)
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

    // TODO
    /*
        private suspend fun doUpdate(
            currentEvent: Flow<Event>,
            flag: Int
        ): Event {
            lateinit var event: Event
            currentEvent.collect {
                val number = if (flag == 0) 0 else 1
                val newEntry = checkUserAlreadyAcceptedOrCancelledAndRemove(it, loggedInUser.userId, number)
                event =  getUpdatedEventEntry(
                    it.id,
                    it.host,
                    it.date,
                    newEntry.accepted,
                    newEntry.cancelled
                ).also { event ->
                    updateEvent(event)
                }
            }
            return event
        }
    */

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

}