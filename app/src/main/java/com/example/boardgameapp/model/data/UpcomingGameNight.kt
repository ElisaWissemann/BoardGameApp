package com.example.boardgameapp.model.data

data class UpcomingGameNight (
    val gameNightId: Int,
    val host: String,
    val date: String,
    val accepted: ArrayList<Int>?,
    val cancelled: ArrayList<Int>?
        )