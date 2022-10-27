package com.example.boardgameapp.data

import android.util.Log
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.game.Game
import kotlinx.coroutines.flow.Flow

class BoardGameRepository(private val dao: BoardGameDao) {

    //val games = dao.getAllGames()

    suspend fun insertGame(game: Game): Long {
        return dao.insertGame(game)
    }

    suspend fun deleteGame(game: Game): Int {
        return dao.deleteGame(game)
    }

    suspend fun insertEvent(event: Event): Long {
        return dao.insertEvent(event)
    }

    suspend fun deleteEvent(event: Event): Int {
        return dao.deleteEvent(event)
    }

    val events: Flow<MutableList<Event>> = dao.getAllEvents()


}