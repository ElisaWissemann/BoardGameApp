package com.example.boardgameapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.boardgameapp.data.entities.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//TODO: Add more tests

@RunWith(AndroidJUnit4::class)
@SmallTest
class BoardGameDaoTest {
    // use standardTestDispater or UnconfinedTestDispatcher
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    //private val testDispatcher = StandardTestDispatcher()
    // For coroutines that collect values
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(dispatcher)

    private lateinit var database: BoardGameDatabase
    private lateinit var dao: BoardGameDao


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        //Set up a Room Database that is holt in Memory, only for testing
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), BoardGameDatabase::class.java
        ).setTransactionExecutor(dispatcher.asExecutor())
            .setQueryExecutor(dispatcher.asExecutor()).build()
        dao = database.boardGameDao
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        database.close()
        Dispatchers.resetMain()
    }

    /*--------*---------Events----------*-------*/
//TODO: read https://developer.android.com/kotlin/flow/test
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertEventTest() = testScope.runTest {
        val event = Event(5, 3, "24.12.2023", arrayListOf(1, 2), arrayListOf(4, 5))
        dao.insertEvent(event)
        val getEventsFromDB = dao.getEvents()
        assertEquals(getEventsFromDB,   flow{emit(event)})
    }

    @Test
    fun deleteEventTest() = testScope.runTest {
        val event = Event(5, 3, "24.12.2023", arrayListOf(1, 2), arrayListOf(4, 5))
        dao.insertEvent(event)
        advanceUntilIdle()
        dao.deleteEvent(event)

        val getEventsFromDB = dao.getEvents()
        println(getEventsFromDB.collect().toString())

        assertEquals(emptyFlow<List<Event>>(), getEventsFromDB)
    }
}