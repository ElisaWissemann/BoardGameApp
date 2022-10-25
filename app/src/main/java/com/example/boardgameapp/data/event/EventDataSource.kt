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
            mutableListOf("Emma", "Stefan", "Rainhardt"), mutableListOf("Anna")
        ),
        Event(
            2,
            2,
            "22.11.2022",
            mutableListOf("Anna", "Frank"), mutableListOf("Anna")
        ),
        Event(
            3,
            3,
            "22.12.2022",
            mutableListOf("Anna"), mutableListOf("Stefanie", "Frank")
        ),
        Event(
            4,
            1,
            "22.01.2023",
            mutableListOf("Anna", "Frank", "Dieter", "Steffi"),  mutableListOf()
        ),
        Event(
            5,
            2,
            "22.02.2023",
            mutableListOf("Anna", "Frank", "Steffi"),  mutableListOf("Frank")
        )
    )
}