package com.example.boardgameapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity (tableName = "eventFoodCrossRef", primaryKeys = ["eventId", "foodId"])
data class EventFoodCrossRef (
    @ColumnInfo(name = "eventId")
    val eventId: Int,
    @ColumnInfo(name= "foodId")
    val foodId: Int)

