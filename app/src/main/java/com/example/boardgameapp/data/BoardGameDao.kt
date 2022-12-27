package com.example.boardgameapp.data

import androidx.room.*
import com.example.boardgameapp.data.entities.*
import kotlinx.coroutines.flow.Flow

// TODO Bodo is it a good practice to have all here? What if there are 1000 methods?

@Dao
interface BoardGameDao {

    /*-----------------Users-----------------*/
    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM users")
    fun getAllUsersNoFlow(): List<User>

    @Query("SELECT * FROM users WHERE userId= :id")
    fun getUser(id: Int): Flow<User>

    @Query("SELECT name FROM users WHERE userId= :id")
    fun getUserName(id: Int): String

    @Update
    suspend fun updateUser(user: User)


    /*--------*---------Events----------*-------*/
    /*Insert*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: Event): Long

    /*Delete*/
    @Delete
    fun deleteEvent(event: Event): Int

    /*GET*/
    @Query("SELECT * FROM events")
    fun getEvents(): Flow<List<Event>>

    @Query("SELECT * FROM events")
    fun getAllEventsNoFlow(): List<Event>

    @Query("SELECT * FROM events WHERE eventId= :id")
    fun getEvent(id: Int): Flow<Event>

    @Update
    suspend fun updateEvent(event: Event)


    /*-----------------LoggedInUser-----------------*/

    @Query("SELECT * FROM loggedInUser WHERE rowId= :id")  //TODO Bodo is it always id=0? then you don't have to pass it
    fun loggedInUser(id: Int): LoggedInUser

    /*-----------------Games-----------------*/

    @Query("SELECT * FROM games")
    fun getGames(): Flow<List<Game>>

    @Query("SELECT name FROM games")
    fun getGamesArray(): Flow<Array<String>>

    @Query("SELECT gameId FROM games WHERE name = :game")
    fun getGameId(game: String): Int

    /*-----------------FoodStyles-----------------*/

    @Query("SELECT * FROM foodStyles")
    fun getFoodStyles(): Flow<List<FoodStyles>>

    @Query("SELECT foodStyle FROM foodStyles")
    fun getFoodStylesArray(): Flow<Array<String>>

    /*-----------------EventGameCrossRef-----------------*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEventGameCrossRef(eventGameCrossRef: EventGameCrossRef)

    @Query("SELECT name FROM eventGameCrossRef INNER JOIN games ON games.gameId = eventGameCrossRef.gameId WHERE eventId = :id ")
    fun getEventsSuggestedGameNames(id: Int): Flow<List<String>>

    // TODO Bodo Add a README.md with explanation of the app + (database-)diagrams and explanations of the entities (eventGameCrossRef is not clear just from reading
}