package com.example.boardgameapp.data.event

/**
 * An object to generate a static list of events*/
object EventDataSource {
    //TODO: Change date to type Date

    val events: List<Event> = listOf(
        Event(
            1,
            1,
            "22.10.2022",
            listOf("Emma", "Stefan", "Rainhardt"), listOf("Anna")
        ),
        Event(
            2,
            2,
            "22.11.2022",
            listOf("Anna", "Frank"), listOf("Anna")
        ),
        Event(
            3,
            3,
            "22.12.2022",
            listOf("Anna"), listOf("Stefanie", "Frank")
        ),
        Event(
            4,
            1,
            "22.01.2023",
            listOf("Anna", "Frank", "Dieter", "Steffi"),  listOf()
        ),
        Event(
            5,
            2,
            "22.02.2023",
            listOf("Anna", "Frank", "Steffi"),  listOf("Frank")
        )
    )
}