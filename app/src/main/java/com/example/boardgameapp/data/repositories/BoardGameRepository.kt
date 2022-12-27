package com.example.boardgameapp.data.repositories

import com.example.boardgameapp.data.BoardGameDao
import com.example.boardgameapp.data.entities.Event
import com.example.boardgameapp.data.entities.LoggedInUser
import com.example.boardgameapp.data.entities.User
import com.example.boardgameapp.data.dto.GameNight
import com.example.boardgameapp.data.dto.UpcomingGameNight
import com.example.boardgameapp.data.entities.EventGameCrossRef
import kotlinx.coroutines.flow.*


//TODO: Get rid of NoFlow requests
class BoardGameRepository(private val dao: BoardGameDao): BoardGameRepositoryInterface {

    /*--------*---------Users----------*-------*/

    //ONLY USED IN PROFILE- CLEANUP
    fun getAllUsersNoFlow(): List<User> {
        return dao.getAllUsersNoFlow()
    }

    /**
     * Function to get an existing User from the DB
     **/
    override fun getUserStream(id: Int): Flow<User> {
        return dao.getUser(id)
    }

    /**
     * Function to get UserName
     * */
    override fun getUserName(id: Int): String {
        return dao.getUserName(id)
    }

    /**
     * Function to update the existing User in the DB
     * */
    override suspend fun updateUser(user: User) {
        return dao.updateUser(user)
    }

    /*--------*---------Events----------*-------*/

    override fun getEventStream(id: Int): Flow<Event> {
        return dao.getEvent(id)
    }

    //ONLY USED IN PROFILE- CLEANUP
    override fun getAllEventsNoFlow(): List<Event> {
        return dao.getAllEventsNoFlow()
    }

    /**
     * Function to update the existing Event in the DB
     * */
    override suspend fun updateEvent(event: Event) {
        return dao.updateEvent(event)
    }

    /*--------*---------LoggedIn----------*-------*/
    override fun loggedInUser(): LoggedInUser {
        return dao.loggedInUser(0)
    }

    /*--------*---------GameNight----------*-------*/
    /**
     * Function that combines events and user to a specific GameNight Object
     * */
    //Todo: Don't pass userId it can be accessed via eventId
    //change to Join
    override fun retriveGameNight(eventId: Int, userId: Int): Flow<GameNight> {

        val event = dao.getEvent(eventId)
        val user = dao.getUser(userId)

        val gameNight = event.combine(user) { event, user ->
            GameNight(
                gameNightId = event.id,
                hostId = user.id,  // TODO Bodo what if I pass another userid? Why do I need it, can't you store the hostId in the gameNight object?
                host = user.name,
                date = event.date,
                hostRating = user.rating
            )
        }

        return gameNight
    }

    /**
     * Function that combines events and user to a UpcomingGameNight Object
     * */
    //TODO: change to https://developer.android.com/training/data-storage/room/relationships
    override fun getUpcomingEvents(): Flow<List<UpcomingGameNight?>> {

        val upcomingGameNightsList = combine(
            dao.getEvents(), dao.getUsers()
        ) { gameNights, users ->
            gameNights.map { gameNight ->
                users.find { it.id == gameNight.host }?.let {
                    UpcomingGameNight(
                        gameNightId = gameNight.id,
                        hostId = it.id,
                        host = it.name,
                        date = gameNight.date,
                        accepted = gameNight.accepted,
                        cancelled = gameNight.cancelled,
                    )
                }
            }
        }
        return upcomingGameNightsList
    }

    /*--------*---------Games----------*-------*/
    override fun getGamesArray(): Flow<Array<String>> {
        return dao.getGamesArray()
    }

    override fun getGameId(game:String): Int{
        return dao.getGameId(game)
    }

    override suspend fun addEventGameCrossRef(eventGameCrossRef: EventGameCrossRef){
        return dao.insertEventGameCrossRef(eventGameCrossRef )
    }

     override fun getEventsSuggestedGameNames(eventId: Int): Flow<List<String>>{
        return dao.getEventsSuggestedGameNames(eventId)
    }

    /*--------*---------FoodStyles----------*-------*/
    override fun getFoodStylesArray(): Flow<Array<String>> {
        return dao.getFoodStylesArray()
    }
}
