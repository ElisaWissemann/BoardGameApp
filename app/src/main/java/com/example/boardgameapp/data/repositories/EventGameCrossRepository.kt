package com.example.boardgameapp.data.repositories

import androidx.room.Insert
import com.example.boardgameapp.data.BoardGameDao
import com.example.boardgameapp.data.entities.EventGameCrossRef
import kotlinx.coroutines.flow.Flow

class EventGameCrossRepository (private val dao: BoardGameDao)  {

    fun getGamesArray(): Flow<Array<String>> {
        return dao.getGamesArray()
    }

    fun getGameId(game:String): Int{
        return dao.getGameId(game)
    }

    suspend fun addEventGameCrossRef(eventGameCrossRef: EventGameCrossRef){
        return dao.insertEventGameCrossRef(eventGameCrossRef )
    }
}