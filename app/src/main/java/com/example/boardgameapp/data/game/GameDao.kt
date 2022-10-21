package com.example.boardgameapp.data.game

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert
    fun insertGame(game: Game)

    @Delete
    fun delete(game: Game)

    @Query("SELECT * FROM games")
    fun getAll(): Flow<List<Game>>
}