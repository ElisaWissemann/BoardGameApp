package com.example.boardgameapp.repositories.dto

import java.sql.RowId

data class UpcomingGameNight (
    val gameNightId: Int,
    val hostId: Int,
    val host: String,
    val date: String,
    val accepted: ArrayList<Int>?,
    val cancelled: ArrayList<Int>?
        )