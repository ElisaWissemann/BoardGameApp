package com.example.boardgameapp.data

import androidx.room.*
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.game.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface BoardGameDao {

    /*-----------------Event-----------------*/
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event): Long

    @Delete
    fun deleteEvent(event: Event): Int

    @Query("SELECT * FROM events")
//    TODO: change to Flows in the future fun getAllGames(): Flow<List<Game>>
//     passing back Lists can cause errors
    fun getAllEvents(): Flow<MutableList<Event>>

    /*-----------------Game-----------------*/
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: Game) : Long

    @Delete
    fun deleteGame(game: Game): Int

//    @Query("SELECT * FROM games")
//    //TODO: change to Flows in the future fun getAllGames(): Flow<List<Game>>
//    // passing back Lists can cause errors
//    fun getAllGames(): List<Game>
}