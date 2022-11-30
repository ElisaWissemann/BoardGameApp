package com.example.boardgameapp.data

import androidx.room.*
import com.example.boardgameapp.data.entities.Event
import com.example.boardgameapp.data.entities.Game
import com.example.boardgameapp.data.entities.User
import com.example.boardgameapp.data.entities.LoggedInUser
import kotlinx.coroutines.flow.Flow

@Dao
interface BoardGameDao {
    /*-----------------Users-----------------*/


    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM users")
    fun getAllUsersNoFlow(): List<User>

    @Query("SELECT * FROM users WHERE userId= :id")
    fun getUser(id:Int): Flow<User>

    @Query("SELECT name FROM users WHERE userId= :id")
    fun getUserName(id:Int): String

    @Update
    suspend fun updateUser(user:User)



    /*-----------------Events-----------------*/


    /*GET*/
    @Query("SELECT * FROM events")
    fun getEvents(): Flow<List<Event>>

    @Query("SELECT * FROM events")
    fun getAllEventsNoFlow(): List<Event>

    @Query("SELECT * FROM events WHERE eventId= :id")
    fun getEvent(id:Int): Flow<Event>

    @Update
    suspend fun updateEvent(event: Event)


    /*Insert*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event): Long

    /*Delete*/
    @Delete
    fun deleteEvent(event: Event): Int

    /*-----------------LoggedInUser-----------------*/

    @Query("SELECT * FROM loggedInUser WHERE rowId= :id")
    fun loggedInUser(id: Int): LoggedInUser

    /*-----------------Games-----------------*/

    @Query("SELECT * FROM games")
    fun getGames(): Flow<List<Game>>

    @Query("SELECT name FROM games")
    fun getGamesArray(): Flow<Array<String>>

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertGame(game: Game): Long
//
//    @Delete
//    fun deleteGame(game: Game): Int

}