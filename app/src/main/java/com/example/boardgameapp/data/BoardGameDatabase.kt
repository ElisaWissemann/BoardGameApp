package com.example.boardgameapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.boardgameapp.data.converters.DoubleArrayListConverter
import com.example.boardgameapp.data.converters.IntArrayListConverter
import com.example.boardgameapp.data.entities.*

// Tutorials: https://appdevnotes.com/android-mvvm-project-example/
//https://www.youtube.com/watch?v=iTdzBM1zErA
//Codelab:
//https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-5-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-intro-room-flow#8

@Database(entities = [User::class, Event::class, LoggedInUser::class, Game::class, FoodStyles::class, EventGameCrossRef::class], version = 1, exportSchema = true)
@TypeConverters(IntArrayListConverter::class, DoubleArrayListConverter::class)
abstract class BoardGameDatabase : RoomDatabase() {
    abstract val boardGameDao: BoardGameDao

    companion object {
        //Add Default data https://medium.com/@hrithik481/roomdb-in-android-with-kotlin-coroutines-bdb11ae37acb
        @Volatile
        private var INSTANCE: BoardGameDatabase? = null
        fun getInstance(context: Context): BoardGameDatabase {
            synchronized(this) { // TODO Bodo does it make sense to have a synchronized database + suspend functions?
                return INSTANCE ?: Room.databaseBuilder(
                        context,
                        BoardGameDatabase::class.java,
                        "board_game_database"
                    ).allowMainThreadQueries().createFromAsset("database/db_prepop.db").build().also {
                        INSTANCE = it
                }
            }
        }
    }
}