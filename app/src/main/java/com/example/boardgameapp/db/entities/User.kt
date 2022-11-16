package com.example.boardgameapp.db.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.boardgameapp.db.converters.DoubleArrayListConverter
import com.example.boardgameapp.db.converters.IntArrayListConverter

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") @NonNull val id: Int,
    @ColumnInfo(name = "name") @NonNull val name: String,
    @ColumnInfo(name = "surname") val surname: String?,
    @ColumnInfo (name="address")val address: String?,
    @TypeConverters(IntArrayListConverter::class)
    @ColumnInfo(name ="hosted_events") val hosted_events: ArrayList<Int>?,
    @ColumnInfo(name ="favorite_game") val favorite_game: String?,
    @ColumnInfo(name = "favorite_food") val favorite_food: String?,
    @TypeConverters(DoubleArrayListConverter::class)
    @ColumnInfo(name = "rating")val rating: ArrayList<Double>?
)
