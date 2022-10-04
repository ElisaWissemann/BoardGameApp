package com.example.boardgameapp.data.event

import java.util.Date

/**
 * A data class to represent the information presented in the Event Screen*/
data class Event(
    val id: Int,
    val host: String,
    //only for testint will be changed to Date
    val date: Int,
    //only for testing will be changed to an array/list
    val accepted: Int,
    val cancelled: Int
)
