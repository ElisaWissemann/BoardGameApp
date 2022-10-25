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
            arrayListOf("Emma", "Stefan", "Rainhardt"), arrayListOf("Anna")
        ),
        Event(
            2,
            2,
            "22.11.2022",
            arrayListOf("Anna", "Frank"), arrayListOf("Anna")
        ),
        Event(
            3,
            3,
            "22.12.2022",
            arrayListOf("Anna"), arrayListOf("Stefanie", "Frank")
        ),
        Event(
            4,
            1,
            "22.01.2023",
            arrayListOf("Anna", "Frank", "Dieter", "Steffi"),  arrayListOf()
        ),
        Event(
            5,
            2,
            "22.02.2023",
            arrayListOf("Anna", "Frank", "Steffi"),  arrayListOf("Frank")
        )
    )
}