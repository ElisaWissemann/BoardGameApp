package com.example.boardgameapp.data

import com.example.boardgameapp.data.entities.Event
import com.example.boardgameapp.data.entities.User

object TestFixtures {
    fun dummyUser1(
        id: Int = 1,
        name: String = "Peter",
        surname: String = "Lustig",
        address: String = "BerlinerWeg 5",
        hosted_events: ArrayList<Int> = arrayListOf(2, 3),
        favorite_game: String = "Monopoly",
        favorite_food: String = "Asia",
        rating: ArrayList<Double> = arrayListOf(1.0, 3.0)
    ) = User(
        id = id,
        name = name,
        surname = surname,
        address = address,
        hosted_events = hosted_events,
        favorite_game = favorite_game,
        favorite_food = favorite_food,
        rating = rating
    )
    fun dummyEvent1(
        id: Int = 5,
        host: Int = 3,
        date: String = "24.12.2023",
        accepted: ArrayList<Int> = arrayListOf(1, 2),
        cancelled: ArrayList<Int> = arrayListOf(4, 5)
    ) = Event(
        id = id,
        host = host,
        date = date,
        accepted = accepted,
        cancelled = cancelled
    )

}

