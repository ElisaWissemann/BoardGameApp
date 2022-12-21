package com.example.boardgameapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.boardgameapp.data.converters.IntArrayListConverter

@Entity ( tableName = "eventGameCrossRef", primaryKeys = ["eventId", "gameId"])
data class EventGameCrossRef(
    @ColumnInfo(name ="eventId")
    val eventId: Int,
    @ColumnInfo(name ="gameId")
    val gameId: Int,
    @TypeConverters(IntArrayListConverter::class)
    @ColumnInfo(name = "rating") val voting: ArrayList<Int>
)