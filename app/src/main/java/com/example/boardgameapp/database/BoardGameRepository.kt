package com.example.boardgameapp.database

import com.example.boardgameapp.database.entities.Event
import com.example.boardgameapp.database.game.Game
import com.example.boardgameapp.database.entities.User

class BoardGameRepository(private val dao: BoardGameDao) {

    //val games = dao.getAllGames()

    /*Users*/
    val users: MutableList<User> = dao.getAllUsers()

    /*Events*/
    val events: MutableList<Event> = dao.getAllEvents()

    suspend fun insertEvent(event: Event): Long {
        return dao.insertEvent(event)
    }

     fun deleteEvent(event: Event): Int {
        return dao.deleteEvent(event)
    }

    /*Games*/
    suspend fun insertGame(game: Game): Long {
        return dao.insertGame(game)
    }

     fun deleteGame(game: Game): Int {
        return dao.deleteGame(game)
    }


}