package com.example.boardgameapp.db

import androidx.room.*
import com.example.boardgameapp.db.entities.Event
import com.example.boardgameapp.db.game.Game
import com.example.boardgameapp.db.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface BoardGameDao {

    /*-----------------Users-----------------*/
    /*GET*/
    @Query("SELECT * FROM users")
    fun getUsersStream(): Flow<List<User>>

    @Query("SELECT * FROM users")
    fun getAllUsersNoFlow(): List<User>

    @Query("SELECT * FROM users WHERE id= :id")
    fun getUser(id:Int): Flow<User>

    @Query("SELECT rating FROM users WHERE id = :id")
    fun getUserRating(id:Int): Flow<String>

    /*-----------------Events-----------------*/
    /*GET*/
    @Query("SELECT * FROM events")
    fun getAllEventsStream(): Flow<List<Event>>

    @Query("SELECT * FROM events")
    fun getAllEventsNoFlow(): List<Event>

    @Query("SELECT * FROM events WHERE id= :id")
    fun getEvent(id:Int): Flow<Event>


    /*Insert*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event): Long

    /*Delete*/
    @Delete
    fun deleteEvent(event: Event): Int


    /*-----------------Games-----------------*/
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertGame(game: Game): Long
//
//    @Delete
//    fun deleteGame(game: Game): Int

//    @Query("SELECT * FROM games")
//    //TODO: change to Flows in the future fun getAllGames(): Flow<List<Game>>
//    // passing back Lists can cause errors
//    fun getAllGames(): List<Game>
}