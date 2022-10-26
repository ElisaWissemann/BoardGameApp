package com.example.boardgameapp.data.event

import java.util.Date

/**
 * A data class to represent the information presented in the Event Screen*/
data class Event(
    val id: Int,
    val host: Int,
    //only for testing will be changed to Date
    val date: String,
    //only for testing will be changed to an array/list
    val accepted: List<String>,
    val cancelled: List<String>

)
