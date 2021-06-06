package com.example.dotify.manager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.dotify.DotifyApplication
import kotlin.random.Random

class SongRefreshWorker(
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val dataRepository by lazy { dotifyApp.dataRepository }
    private val dotifyApp by lazy { context.applicationContext as DotifyApplication }
    private val songNotificationManager by lazy { dotifyApp.notificationManager }

    override suspend fun doWork(): Result {

        val allSongs = dataRepository.getAllSongs()
        var randomNum = Random.nextInt(0, allSongs.songs.size)
        dotifyApp.songManager.onSongSelected(allSongs.songs[randomNum])

        songNotificationManager.publishNewSongNotification()

        return Result.success()
    }


}