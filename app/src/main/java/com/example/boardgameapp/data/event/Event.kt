package com.example.boardgameapp.data.event

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.boardgameapp.data.StringListConverter

/**
 * A data class to represent the information presented in the Event Screen*/
//@Entity(tableName = "events")
//data class Event(
//    @PrimaryKey @ColumnInfo(name = "rowid") val id: Int,
//    @ColumnInfo(name = "host") val host: Int,
//    //only for testing will be changed to Date
//    @ColumnInfo(name = "date") val date: String,
//    //only for testing will be changed to an array/list
//    @ColumnInfo(name = "accepted") @field:TypeConverters(StringListConverter) val accepted: MutableList<String>,
//    @ColumnInfo(name = "cancelled") val cancelled: MutableList<String>
//)

data class Event(
    val id: Int,
    val host: Int,
    //only for testing will be changed to Date
    val date: String,
    //only for testing will be changed to an array/list
    val accepted: MutableList<String>,
    val cancelled: MutableList<String>
)
