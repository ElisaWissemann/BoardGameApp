package com.example.boardgameapp.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loggedInUser")
data class LoggedInUser(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowId") @NonNull val rowId: Int,
    @ColumnInfo(name = "userId") @NonNull val userId: Int,
    )
