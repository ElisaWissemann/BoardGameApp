package com.example.boardgameapp.repositories

import android.util.Log
import com.example.boardgameapp.database.BoardGameDao
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.entities.Game
import com.example.boardgameapp.database.entities.LoggedInUser
import com.example.boardgameapp.database.entities.User
import com.example.boardgameapp.database.dto.GameNight
import com.example.boardgameapp.database.dto.UpcomingGameNight
import kotlinx.coroutines.flow.*

class BoardGameRepository(private val dao: BoardGameDao) {

    /*------Users ------*/
    fun getUsersStream(): Flow<List<User>> {
        return dao.getUsers()
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
     * Function to get UserName
     * */
    fun getUserName(id:Int):String{
        return dao.getUserName(id)
    }
    /**
     * Function to update the existing User in the DB
     * */
    suspend fun updateUser(user: User) {
        return dao.updateUser(user)
    }

    /*------Events------*/
    fun getEventsStream(): Flow<List<Event>> {
        return dao.getEvents()
    }

     fun getEventStream(id: Int): Flow<Event> {
        return dao.getEvent(id)
    }

    fun getAllEventsNoFlow(): List<Event> {
        return dao.getAllEventsNoFlow()
    }

    /**
     * Function to update the existing Event in the DB
     * */
    suspend fun updateEvent(event:Event) {
        return dao.updateEvent(event)
    }

    /*---------LoggedIn-------------*/
    fun loggedInUser(): LoggedInUser {
        return dao.loggedInUser(0)
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
    fun getUpcomingEvents(): Flow<List<UpcomingGameNight?>> {

        val upcomingGameNightsList = combine(
            dao.getEvents(), dao.getUsers()
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

    /*------------Games-----------------*/

     fun getGamesArray(): Flow<Array<String>>{
         return  dao.getGamesArray()
     }
//
//    suspend inline fun <reified T> Flow<List<T>>.flattenToArrayList() =
//        flatMapConcat { it.asFlow() }.toList().toTypedArray()
//
//    suspend fun getGamesArrayList(): ArrayList<Game>{
//        val games = dao.getGames()
//        val new =  games.flattenToArrayList()
//        val array = arrayListOf<String>()
//        new.onEach { array.plus(it.name)
//
//        }
//    }
//    suspend fun getGamesArrayList(): ArrayList<Game>{
//       val games =  dao.getGames()
//        games
//       val newGames = games.collect{ game ->
//            game.map { it.name }.toTypedArray()
//        }
//        return newGames
//    }
}
