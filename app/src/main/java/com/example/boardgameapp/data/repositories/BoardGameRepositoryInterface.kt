package com.example.boardgameapp.data.repositories

import com.example.boardgameapp.data.dto.GameNight
import com.example.boardgameapp.data.dto.UpcomingGameNight
import com.example.boardgameapp.data.entities.Event
import com.example.boardgameapp.data.entities.EventGameCrossRef
import com.example.boardgameapp.data.entities.LoggedInUser
import com.example.boardgameapp.data.entities.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine


interface BoardGameRepositoryInterface {

    /*------Users ------*/

//    fun getAllUsersNoFlow(): List<User>

    /**
     * Function to get an existing User from the DB
     **/
    fun getUserStream(id: Int): Flow<User>

    /**
     * Function to get UserName
     * */
    fun getUserName(id: Int): String

    /**
     * Function to update the existing User in the DB
     * */
    suspend fun updateUser(user: User)

    /*--------*---------Events----------*-------*/


    fun getEventStream(id: Int): Flow<Event>

    fun getAllEventsNoFlow(): List<Event>

    /**
     * Function to update the existing Event in the DB
     * */
    suspend fun updateEvent(event: Event)

    /*---------LoggedIn-------------*/
    fun loggedInUser(): LoggedInUser

    /*--------*---------GameNight----------*-------*/
    /**
     * Function that combines events and user to a specific GameNight Object
     * */
    fun retriveGameNight(eventId: Int, userId: Int): Flow<GameNight>

    /**
     * Function that combines events and user to a UpcomingGameNight Object
     * */
    fun getUpcomingEvents(): Flow<List<UpcomingGameNight?>>

    /*------------Games-----------------*/

    fun getGamesArray(): Flow<Array<String>>

    fun getGameId(game:String): Int

    suspend fun addEventGameCrossRef(eventGameCrossRef: EventGameCrossRef)

    fun getEventsSuggestedGameNames(eventId: Int): Flow<List<String>>

    /*------------FoodStyles-----------------*/

    fun getFoodStylesArray(): Flow<Array<String>>
}