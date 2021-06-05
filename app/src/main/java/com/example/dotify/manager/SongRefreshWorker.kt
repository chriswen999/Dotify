package com.example.dotify.manager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.dotify.DotifyApplication

class SongRefreshWorker(
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val dotifyApp by lazy { context.applicationContext as DotifyApplication }
    private val songNotificationManager by lazy { dotifyApp.notificationManager }

    override suspend fun doWork(): Result {

        Log.i("EmailSyncWorker", "syncing emails now")
        songNotificationManager.publishNewSongNotification()

        return Result.success()
    }


}