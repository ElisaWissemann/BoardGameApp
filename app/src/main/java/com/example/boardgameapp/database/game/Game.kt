package com.example.boardgameapp.database.game

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** A data class that represents the GamesList*/
@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") @NonNull val name: String
)
