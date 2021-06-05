package com.example.dotify.manager

import android.content.Context
import androidx.constraintlayout.widget.Constraints
import androidx.work.*
import java.util.concurrent.TimeUnit


private const val SONG_REFRESH_WORK_TAG = "SONG_REFRESH_WORK_TAG"

class RefreshSongManager(context: Context) {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    fun refreshEmails() {

        val request = OneTimeWorkRequestBuilder<SongRefreshWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                androidx.work.Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_REFRESH_WORK_TAG)
            .build()

        workManager.enqueue(request)
    }

    fun startRefreshEmailsPeriodically() {
        if (isSongRefreshRunning()) {
            return
        }

        val request = PeriodicWorkRequestBuilder<SongRefreshWorker>(20, TimeUnit.MINUTES)
            .setConstraints(
                androidx.work.Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_REFRESH_WORK_TAG)
            .build()

        workManager.enqueue(request)

    }

    fun stopPeriodicallyRefreshing() {
        workManager.cancelAllWorkByTag(SONG_REFRESH_WORK_TAG)
    }

    fun doWorkEveryTwoDay() {
        val request = PeriodicWorkRequestBuilder<SongRefreshWorker>(2, TimeUnit.DAYS)
            .setConstraints(
                androidx.work.Constraints.Builder()
                    .setRequiresBatteryNotLow(true)
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_REFRESH_WORK_TAG)
            .build()
    }

    private fun isSongRefreshRunning(): Boolean {
        return workManager.getWorkInfosByTag(SONG_REFRESH_WORK_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }




}