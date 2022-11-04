package com.example.boardgameapp.database

import androidx.room.*
import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.game.Game
import com.example.boardgameapp.database.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface BoardGameDao {

    /*-----------------Users-----------------*/
    /*GET*/
    @Query("SELECT * FROM users")
    //    TODO: change to Flows
    fun getAllUsers(): MutableList<User>

    /*-----------------Events-----------------*/
    /*GET*/
    @Query("SELECT * FROM events")
    //    TODO: change to Flows in the future fun getAllGames(): Flow<List<Game>>
    //     passing back Lists can cause errors
    fun getAllEvents(): Flow<List<Event>>

    @Query("SELECT * FROM events")
    //    TODO: change to Flows in the future fun getAllGames(): Flow<List<Game>>
    //     passing back Lists can cause errors
    fun getAllEventsNoFlow(): List<Event>

    /*Insert*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event): Long

    /*Delete*/
    @Delete
    fun deleteEvent(event: Event): Int


    /*-----------------Games-----------------*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: Game): Long

    @Delete
    fun deleteGame(game: Game): Int

//    @Query("SELECT * FROM games")
//    //TODO: change to Flows in the future fun getAllGames(): Flow<List<Game>>
//    // passing back Lists can cause errors
//    fun getAllGames(): List<Game>
}