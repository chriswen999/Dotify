package com.example.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.dotify.DotifyApplication
import com.example.dotify.R
import com.example.dotify.activity.PlayerDetailActivity
import com.example.dotify.activity.SONG_INFO_KEY
import com.example.dotify.model.Song
import kotlin.random.Random

private const val NEW_SONGS_CHANNEL_ID = "NEW_SONGS_CHANNEL_ID"

class SongNotificationManager(
    private val context: Context
) {
    private val notificationManager = NotificationManagerCompat.from(context)
    private val dotifyApp: DotifyApplication = context.applicationContext as DotifyApplication

    init {
        // Initialize all channels
        initNotificationChannels()
    }

    fun publishNewSongNotification() {
        var selectedSong: Song? = dotifyApp.songManager.selectedSong

        // Define the intent or action you want when user taps on notification
        val intent = Intent(context, PlayerDetailActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
 /*           putExtra(SONG_INFO_KEY, selectedSong)
            if (selectedSong != null) {
                dotifyApp.songManager.onSongSelected(selectedSong)
            }*/
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT) // dont forget to add PendingIntent.FLAG_UPDATE_CURRENT to send data over


        // Build information you want the notification to show
        val builder = NotificationCompat.Builder(context, NEW_SONGS_CHANNEL_ID)    // channel id from creating the channel
            .setSmallIcon(R.drawable.ic_song)
            .setContentTitle("${selectedSong?.artist} just released a new song!!!")
            .setContentText("Listen to ${selectedSong?.title} now on Dotify")
            .setContentIntent(pendingIntent)    // sets the action when user clicks on notification
            .setAutoCancel(true)    // This will dismiss the notification tap
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Tell the OS to publish the notification using the info
        with(notificationManager) {
            // notificationId is a unique int for each notification that you must define
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }
    }

    private fun initNotificationChannels() {
        initNewSongsChannel()
    }

    private fun initNewSongsChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Info about the channel
            val name = context.getString(R.string.new_songs)
            val descriptionText = context.getString(R.string.new_songs_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // Create channel object
            val channel = NotificationChannel(NEW_SONGS_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Tell the Android OS to create a channel
            notificationManager.createNotificationChannel(channel)
        }
    }


}