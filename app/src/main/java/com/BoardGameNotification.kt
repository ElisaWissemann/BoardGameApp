package com

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

import com.example.boardgameapp.MainActivity
import com.example.boardgameapp.R


class BoardGameNotification (private val context:Context){

    val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(user: String, delay: String){
        Log.i("ELISA", "showNotification executed")
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        Log.i("ELISA", activityPendingIntent.toString())

        val notification = NotificationCompat.Builder(context, BOARDGAME_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_watch_later_24)
            .setContentTitle("Someone is late")
            .setContentText("$user will be $delay min late")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1, notification  )
    }

    companion object{
        const val BOARDGAME_CHANNEL_ID = "boardgame_channel"
    }
}