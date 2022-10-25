package com.example.boardgameapp.data

import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.game.Game

class BoardGameRepository (private val dao: BoardGameDao)  {

    //val games = dao.getAllGames()

    suspend fun insertGame(game: Game): Long {
        return dao.insertGame(game)
    }

    suspend fun deleteGame(game:Game):Int{
        return dao.deleteGame(game)
    }

//    val events = dao.getAllEvents()

    suspend fun insertEvent(event: Event): Long{
        return dao.insertEvent(event)
    }
    suspend fun deleteEvent(event: Event):Int{
        return dao.deleteEvent(event)
    }

}