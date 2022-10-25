package com.example.boardgameapp.data

import android.content.Context
import android.media.metrics.Event
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.boardgameapp.data.game.Game

// Tutorials: https://appdevnotes.com/android-mvvm-project-example/
//https://www.youtube.com/watch?v=iTdzBM1zErA

@Database(entities = [Game::class], version = 1)
@TypeConverters(StringListConverter::class)

abstract class BoardGameDatabase : RoomDatabase() {
    abstract val boardGameDao: BoardGameDao

    companion object {
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