package com.example.boardgameapp.data.dto

data class GameNight(
    val gameNightId: Int,
    val hostId: Int,
    val host: String,
    val date: String,
    val hostRating: ArrayList<Double>?)
