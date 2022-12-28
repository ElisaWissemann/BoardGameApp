package com.example.boardgameapp.ui.event

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValueTest
import com.example.boardgameapp.data.BoardGameDao
import com.example.boardgameapp.data.dto.GameNight
import com.example.boardgameapp.data.repositories.BoardGameRepository
import com.example.boardgameapp.data.repositories.FakeBoardGameRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EventViewModelTest() {
    private val repoMock: BoardGameRepository = mockk()
    private val viewModel = EventViewModel(repoMock)

    //A JUnit Test Rule that swaps the background executor used by the Architecture Components with a different one which executes each task synchronously.
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()

    private val gamesList = listOf("Monopoly", "Scrabble", "Chess")
    private val gameNight = GameNight(666, 1, "Steve", "24.12.2023", arrayListOf(1.0,3.0))

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test to retrievesEventGameNames`() {
        //assign - save dummy data in repo
        // act - function call
        //assert - verify everything is correct
        val eventId = 666
        every {
            repoMock.getEventsSuggestedGameNames(eventId)
        } returns flow { emit(gamesList) }
        val result = viewModel.retrieveEventGameNames(eventId).getOrAwaitValueTest()
        assertThat(result).isEqualTo(gamesList)
    }

    @Test
    fun `test to retrieveGameNights`(){
        val eventId = 666
        val hostId = 1
        every {
            repoMock.retriveGameNight(eventId, hostId)
        } returns flow { emit( gameNight ) }

        val result = viewModel.retrieveGameNight(eventId, hostId).getOrAwaitValueTest()
        assertThat(result).isEqualTo(gameNight)
    }

    @Test
    fun `test to buildUserWithNewRating`(){
        val rating = 1.0f
        val hostId = 1

        every {
            repoMock.getUserStream(hostId)
        }
    }


}