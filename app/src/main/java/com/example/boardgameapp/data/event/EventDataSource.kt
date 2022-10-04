package com.example.boardgameapp.data.event

/**
 * An object to generate a static list of events*/
object EventDataSource {

    val events: List<Event> = listOf(
        Event(
            1,
            "Julia",
            3,
            2,3
        ),
        Event(
            2,
            "Christian",
            3,
            4,1
        ),
        Event(
            3,
            "Anna",
            4,
            1,1
        ),
        Event(
            4,
            "Elisa",
            5,
            3,1
        )
    )
}