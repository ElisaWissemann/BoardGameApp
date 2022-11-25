package com.example.boardgameapp.model.repositories

import com.example.boardgameapp.database.BoardGameDao
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.game.Game
import com.example.boardgameapp.database.entities.User
import com.example.boardgameapp.model.data.UpcomingGameNight
import kotlinx.coroutines.flow.*

class BoardGameRepository(private val dao: BoardGameDao) {

    //val games = dao.getAllGames()

    /*------Users ------*/
    fun getAllUsers(): Flow<List<User>> {
        return dao.getUsers()
    }

    fun getAllUsersNoFlow(): List<User> {
        return dao.getAllUsersNoFlow()
    }

    fun getUser(id: Int): Flow<User> {
        return dao.getUser(id)
    }

    /*------Upcoming Events------*/
     fun getUpcomingEventFlow(): Flow<List<UpcomingGameNight?>> {

        val events = dao.getEvents()
        val users = dao.getUsers()

        val upcomingGameNightsList = combine(
                dao.getEvents(), dao.getUsers()){
            gameNights, users ->
            gameNights.map { gameNight ->
                users.find { it.id == gameNight.host }?.name?.let {
                    UpcomingGameNight(
                        gameNightId = gameNight.id,
                        host = it,
                        date = gameNight.date,
                        accepted = gameNight.accepted,
                        cancelled = gameNight.cancelled,
                    )
                }
            }
        }

        return upcomingGameNightsList
    }

    /*------Events------*/
    fun getAllEvents(): Flow<List<Event>> {
        return dao.getEvents()
    }

    fun getAllEventsNoFlow(): List<Event> {
        return dao.getAllEventsNoFlow()
    }

    suspend fun insertEvent(event: Event): Long {
        return dao.insertEvent(event)
    }

    fun deleteEvent(event: Event): Int {
        return dao.deleteEvent(event)
    }

//    /*Games*/
//    suspend fun insertGame(game: Game): Long {
//        return dao.insertGame(game)
//    }
//
//    fun deleteGame(game: Game): Int {
//        return dao.deleteGame(game)
//    }


}