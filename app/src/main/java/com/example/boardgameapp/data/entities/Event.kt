package com.example.boardgameapp.data.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.boardgameapp.data.converters.IntArrayListConverter

/**
 * A data class to represent the information presented in the Event Screen*/
@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") @NonNull val id: Int,
    @ColumnInfo(name = "host")  val host: Int,
    //only for testing will be changed to Date
    @ColumnInfo(name = "date")  val date: String,
    //only for testing will be changed to an array/list
    @TypeConverters(IntArrayListConverter::class)
    @ColumnInfo(name = "accepted") val accepted: ArrayList<Int>?,
    @TypeConverters(IntArrayListConverter::class)
    @ColumnInfo(name = "cancelled") val cancelled: ArrayList<Int>?
)
