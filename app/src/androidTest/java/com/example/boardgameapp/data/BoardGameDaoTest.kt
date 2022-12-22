package com.example.boardgameapp.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnitRunner
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class BoardGameDaoTest {

    private lateinit var database: BoardGameDatabase
    private lateinit var dao: BoardGameDao

    @Before
    fun setUp() {
        //Set up a Room Database that is holt in Memory, only for testing
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), BoardGameDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.boardGameDao
    }

    @After
    fun tearDown(){
        database.close()
    }
}