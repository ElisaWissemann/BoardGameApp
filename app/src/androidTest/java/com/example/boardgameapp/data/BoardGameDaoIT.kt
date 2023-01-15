package com.example.boardgameapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class BoardGameDaoIT {

    // A JUnit Test Rule that swaps the background executor used by the Architecture Components with a different one which executes each task synchronously.
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        database.close()
        Dispatchers.resetMain()
    }

    /*--------*---------Users----------*-------*/

    /**
     * Testfunction for GetUsers Query
     * */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    //runTest is a coroutine builder designed for testing
    fun GetUsersTest() = testScope.runTest {
        val user = TestFixtures.dummyUser1()
        dao.insertUser(user)

        val result = dao.getUsers().first()
        assertEquals(result, listOf(user))
    }

    /**
     * Testfunction for GetUser with a specific id Query
     * */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun GetUserTest() = testScope.runTest {
        val user = TestFixtures.dummyUser1()
        val userId = user.id
        dao.insertUser(user)

        val result = dao.getUser(userId).first()
        assertEquals(result, user)
    }

    /**
     * Testfunction for GettingUserName by id
     * */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun GetUserNameTest() = testScope.runTest {
        val user  = TestFixtures.dummyUser1()
        val userId = user.id

        dao.insertUser(user)
        val result = dao.getUserName(userId)
        assertEquals(result, user.name)
    }

    /*--------*---------Events----------*-------*/
    /**
     * Testfunction for GetEvents Query
     * */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getEventsTest() = testScope.runTest {
        val  event = TestFixtures.dummyEvent1()
        dao.insertEvent(event)
        val result = dao.getEvents().first()
        assertEquals(result, listOf(event))
    }

    /**
     * Testfunction for GetEvent with a specific id
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getEventTest() = testScope.runTest {
        val event = TestFixtures.dummyEvent1()
        val eventId = event.id
        dao.insertEvent(event)

        val result = dao.getEvent(eventId).first()
        assertEquals(result, event)
    }

}