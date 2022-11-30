package com.example.boardgameapp.data.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.boardgameapp.data.converters.IntArrayListConverter

/**
 * A data class to hold a List of all kind of FoodStyles which the user can choose of*/
@Entity (tableName = "foodStyles")
data class FoodStyles(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "foodId")  @NonNull val id: Int,
    @ColumnInfo(name = "foodStyle")  @NonNull val foodStyle: String,
)



