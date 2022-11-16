package com.example.boardgameapp.db.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** A data class that represents the GamesList*/
@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "game_id") val id: Int,
    @ColumnInfo(name = "name") val name: String
)
