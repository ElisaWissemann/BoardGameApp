package com.example.boardgameapp.repositories

import android.util.Log
import androidx.room.Update
import com.example.boardgameapp.db.BoardGameDao
import com.example.boardgameapp.db.converters.DoubleArrayListConverter
import com.example.boardgameapp.db.entities.Event
import com.example.boardgameapp.db.entities.User
import com.example.boardgameapp.repositories.dto.GameNight
import com.example.boardgameapp.repositories.dto.UpcomingGameNight
import kotlinx.coroutines.flow.*

class BoardGameRepository(private val dao: BoardGameDao) {

    /*------Users ------*/
    fun getUsersStream(): Flow<List<User>> {
        return dao.getUsersStream()
    }

    fun getAllUsersNoFlow(): List<User> {
        return dao.getAllUsersNoFlow()
    }

    /**
     * Function to get an existing User from the DB
     **/
    fun getUserStream(id: Int): Flow<User> {
        return dao.getUser(id)
    }
    /**
     * Function to update the existing User in the DB
     * */
    suspend fun updateUser(user: User) {
        return dao.updateUser(user)
    }

    /*------Events------*/
    fun getEventsStream(): Flow<List<Event>> {
        return dao.getAllEventsStream()
    }

    fun getEventStream(id: Int): Flow<Event> {
        return dao.getEvent(id)
    }

    fun getAllEventsNoFlow(): List<Event> {
        return dao.getAllEventsNoFlow()
    }

    /**
     * Function that combines events and user to a specific GameNight Object
     * */
    fun retriveGameNight(eventId: Int, userId: Int): Flow<GameNight> {

        val event = dao.getEvent(eventId)
        val user = dao.getUser(userId)

        val gameNight = event.combine(user) { event, user ->
            GameNight(
                gameNightId = event.id,
                hostId = user.id,
                host = user.name,
                date = event.date,
                hostRating = user.rating
            )
        }

        return gameNight
    }

    /**
     * Function that combines events and user to a UpcomingGameNight Object
     * */
    fun getUpcomingEventsStream(): Flow<List<UpcomingGameNight?>> {

        val upcomingGameNightsList = combine(
            dao.getAllEventsStream(), dao.getUsersStream()
        ) { gameNights, users ->
            gameNights.map { gameNight ->
                users.find { it.id == gameNight.host }?.let {
                    UpcomingGameNight(
                        gameNightId = gameNight.id,
                        hostId = it.id,
                        host = it.name,
                        date = gameNight.date,
                        accepted = gameNight.accepted,
                        cancelled = gameNight.cancelled,
                    )
                }
            }
        }
        return upcomingGameNightsList
    }
}
//    fun getUserRating(id: Int): ArrayList<Double> {
//        var rating = dao.getUserRating(id)
//        val newRating = arrayListOf<Double>()
//        var converter = DoubleArrayListConverter.newInstance()
//        rating.map {
//            Log.i("ELISA", rating.toString())
//            var newRating = converter.toDoubleArrayList(it)
//        }
//        return newRating
//    }



//    suspend fun insertEvent(event: Event): Long {
//        return dao.insertEvent(event)
//    }
//
//    fun deleteEvent(event: Event): Int {
//        return dao.deleteEvent(event)
//    }