package com.example.boardgameapp.data.user

data class User(
    val id: Int,
    val name: String,
    val surname: String,
    val address: String,
    val hosting_events: List<Int>,
    val favorite_game: String,
    val favorite_food: String,
    val rating: List<Double>
    )
