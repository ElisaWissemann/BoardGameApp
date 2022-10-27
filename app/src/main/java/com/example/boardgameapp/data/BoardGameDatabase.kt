package com.example.boardgameapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.boardgameapp.data.event.Event
import com.example.boardgameapp.data.game.Game

// Tutorials: https://appdevnotes.com/android-mvvm-project-example/
//https://www.youtube.com/watch?v=iTdzBM1zErA

@Database(entities = [Game::class, Event::class], version = 1, exportSchema = false)
@TypeConverters(IntArrayListConverter::class)
abstract class BoardGameDatabase : RoomDatabase() {
    abstract val boardGameDao: BoardGameDao

    companion object {
        //Add Default data https://medium.com/@hrithik481/roomdb-in-android-with-kotlin-coroutines-bdb11ae37acb
        @Volatile
        private var INSTANCE: BoardGameDatabase? = null
        fun getInstance(context: Context): BoardGameDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                        context.applicationContext,
                        BoardGameDatabase::class.java,
                        "board_game_database"
                    ).allowMainThreadQueries().build().also {
                        INSTANCE = it
                }

            }
        }
    }
}