package com.example.boardgameapp.data

import androidx.room.*
import com.example.boardgameapp.data.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BoardGameDao {

    /*-----------------Users-----------------*/

    @Insert
    suspend fun insertUser(user:User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM users")
    fun getAllUsersNoFlow(): List<User>

    @Query("SELECT * FROM users WHERE userId= :id")
    fun getUser(id: Int): Flow<User>

    @Query("SELECT name FROM users WHERE userId= :id")
    fun getUserName(id: Int): String

    /*--------*---------Events----------*-------*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: Event): Long

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event): Int

    /*GET*/
    @Query("SELECT * FROM events")
    fun getEvents(): Flow<List<Event>>

    @Query("SELECT * FROM events")
    fun getAllEventsNoFlow(): List<Event>

    @Query("SELECT * FROM events WHERE eventId= :id")
    fun getEvent(id: Int): Flow<Event>


    /*-----------------LoggedInUser-----------------*/
    @Query("SELECT * FROM loggedInUser WHERE rowId= 0")
    fun loggedInUser(): LoggedInUser

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

    /*-----------------EventFoodCrossRef-----------------*/

    @Query("SELECT foodId FROM foodStyles WHERE foodStyle = :foodstyle")
    fun getFoodStyleId(foodstyle: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEventFoodCrossRef(eventFoodCrossRef: EventFoodCrossRef)

    @Query("SELECT foodStyle FROM eventFoodCrossRef INNER JOIN foodStyles ON foodStyles.foodId = eventFoodCrossRef.foodId WHERE eventId = :id")
    fun getEventsSuggestedFoodNames(id: Int): Flow<List<String>>

}