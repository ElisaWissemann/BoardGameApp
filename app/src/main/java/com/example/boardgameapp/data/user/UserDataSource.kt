package com.example.boardgameapp.data.user

import java.time.temporal.JulianFields

object UserDataSource {
    val users: List<User> = listOf(
        User(
            1,
            "Julia",
            "Schmidt",
            "Musterstraße 4, 27404 Musterstadt",
            listOf(1),
            "Monopoly",
            "greek",
            5.0
        ),
        User(
            2,
            "Christian",
            "Fritz",
            "Hauptstraße 1, 12345 Hauptstadt",
            listOf(2),
            "Siedler von Catan",
            "mexican",
            3.0
        ),
        User(
            3,
            "Elisa",
            "Wissemann",
            "Musterstraße 6, 56789 Musterhausen",
            listOf(3),
            "Chess",
            "asia",
            4.5
        ),
    )
}

