package com.example.boardgameapp.database.user

import com.example.boardgameapp.database.entities.User

//TODO; Read about android repositorys and maybe rename this file
object UserDataSource {
    val users: List<User> = listOf(
        User(
            1,
            "Julia",
            "Schmidt",
            "Musterstraße 4, 27404 Musterstadt",
            arrayListOf(1),
            "Monopoly",
            "greek",
            arrayListOf(4.0, 2.0, 2.0)
        ),
        User(
            2,
            "Christian",
            "Fritz",
            "Hauptstraße 1, 12345 Hauptstadt",
            arrayListOf(2),
            "Siedler von Catan",
            "mexican",
            arrayListOf(3.0)
        ),
        User(
            3,
            "Elisa",
            "Wissemann",
            "Musterstraße 6, 56789 Musterhausen",
            arrayListOf(3),
            "Chess",
            "asia",
            arrayListOf(4.5)
        ),
    )
}

