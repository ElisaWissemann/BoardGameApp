package com.example.boardgameapp.data.dto

/**Data class to display all event in the UpcomingEventsScree/Startscreen*/
data class UpcomingGameNight (
    val gameNightId: Int,
    val hostId: Int,
    val host: String,
    val date: String,
    val accepted: ArrayList<Int>?,
    val cancelled: ArrayList<Int>?
        )