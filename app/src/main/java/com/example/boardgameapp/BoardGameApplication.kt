package com.example.boardgameapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.BoardGameNotification
import com.example.boardgameapp.data.BoardGameDatabase

class BoardGameApplication : Application() {
    val database: BoardGameDatabase by lazy { BoardGameDatabase.getInstance(this) }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 2
            val channel = NotificationChannel(
                BoardGameNotification.BOARDGAME_CHANNEL_ID,
                "BoardGame",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Get notified about delayed players"
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}