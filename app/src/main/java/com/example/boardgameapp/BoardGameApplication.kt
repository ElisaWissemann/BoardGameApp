package com.example.boardgameapp

import android.app.Application
import com.example.boardgameapp.database.BoardGameDatabase

class BoardGameApplication : Application(){
    val database: BoardGameDatabase by lazy {BoardGameDatabase.getInstance(this)}
}