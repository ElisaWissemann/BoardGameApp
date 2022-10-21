package com.example.boardgameapp.data

import android.util.Log
import com.example.boardgameapp.data.event.BoardGameDao
import com.example.boardgameapp.data.game.Game

class BoardGameRepository (private val dao: BoardGameDao)  {

    val games = dao.getAllGames()

    suspend fun insert(game: Game): Long {
        return dao.insertGame(game)
    }

    suspend fun deleteGame(game:Game):Int{
        return dao.deleteGame(game)
    }
}