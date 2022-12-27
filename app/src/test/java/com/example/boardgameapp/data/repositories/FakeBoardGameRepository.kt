package com.example.boardgameapp.data.repositories

import com.example.boardgameapp.data.BoardGameDao
import com.example.boardgameapp.data.dto.GameNight
import com.example.boardgameapp.data.dto.UpcomingGameNight
import com.example.boardgameapp.data.entities.Event
import com.example.boardgameapp.data.entities.EventGameCrossRef
import com.example.boardgameapp.data.entities.LoggedInUser
import com.example.boardgameapp.data.entities.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// Tutorial to handle LiveData
class FakeBoardGameRepository (private val dao: BoardGameDao): BoardGameRepositoryInterface {

     private val user1 = User(
         1,
         "paul",
         "van dyke",
         "Berlinerstraße 5, 13452 Berlin",
         arrayListOf(1),
         "Monopoly",
         "Asia",
         arrayListOf(1.0, 3.0)
     )
     private val user2 = User(
         2,
         "stefan",
         " duke",
         "Bernauer 5, 13452 Berlin",
         arrayListOf(2),
         "Scrabble",
         "Thai",
         arrayListOf(2.0, 4.0)
     )
     private val user3 = User(
         3,
         "peter",
         "vanderhold",
         "katarinenstr 5, 13452 Berlin",
         arrayListOf(3),
         "Uno",
         "Pizza",
         arrayListOf(3.0, 5.0)
     )

     private val event1 = Event(1, 1, "21.12.2023", arrayListOf(), arrayListOf(1))
     private val event2 = Event(2, 2, "2.12.2023", arrayListOf(1, 2), arrayListOf(3))
     private val event3 = Event(3, 3, "1.2.2023", arrayListOf(3), arrayListOf(2))

     private val loggedInUser = LoggedInUser(0, 1)

     private val gameNight = GameNight(1, 1, "paul", "21.12.2023", arrayListOf(1.0, 3.0))

     private val upcomingGameNight1 =
         UpcomingGameNight(1, 1, "paul", "21.12.2023", arrayListOf(), arrayListOf(1))
     private val upcomingGameNight2 =
         UpcomingGameNight(2, 2, "stefan", "2.12.2023", arrayListOf(1, 2), arrayListOf(3))
     private val upcomingGameNight3 =
         UpcomingGameNight(3, 3, "peter", "1.2.2023", arrayListOf(3), arrayListOf(2))

     override fun getUserStream(id: Int): Flow<User> = flow { emit(user1) }
     override fun getUserName(id: Int): String = "Peter"

     override suspend fun updateUser(user: User): Unit = Unit

     override fun getEventStream(id: Int): Flow<Event> = flow { emit(event1) }

     override fun getAllEventsNoFlow(): List<Event> = listOf(event1, event2)

     override suspend fun updateEvent(event: Event) = Unit

     override fun loggedInUser(): LoggedInUser = loggedInUser

     override fun retriveGameNight(eventId: Int, userId: Int): Flow<GameNight> =
         flow { emit(gameNight) }

     override fun getUpcomingEvents(): Flow<List<UpcomingGameNight?>> =
         flow { emit(listOf(upcomingGameNight1, upcomingGameNight2, upcomingGameNight3)) }

     override fun getGamesArray(): Flow<Array<String>> =
         flow { emit(arrayOf("Monopoly", "Scrabble")) }

     override fun getGameId(game: String): Int = 1

     override suspend fun addEventGameCrossRef(eventGameCrossRef: EventGameCrossRef) = Unit

     override fun getEventsSuggestedGameNames(eventId: Int): Flow<List<String>> =
         flow { emit(listOf("Monopoly", "Scrabble", "Uno")) }

     override fun getFoodStylesArray(): Flow<Array<String>> =
         flow { emit(arrayOf("Asia", "Thai", "Pizza")) }

}