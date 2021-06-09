package com.example.dotify

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.manager.RefreshSongManager
import com.example.dotify.manager.SongManager
import com.example.dotify.manager.SongNotificationManager
import com.example.dotify.repository.DataRepository

const val DOTIFY_APP_PREF_KEY = "DOTIFY_APP_PREF_KEY"

class DotifyApplication: Application() {

    var playCount: Int = 0
    lateinit var dataRepository: DataRepository
    lateinit var notificationManager: SongNotificationManager
    lateinit var refreshSongManager: RefreshSongManager

    lateinit var preferences: SharedPreferences

    val  songManager: SongManager by lazy { SongManager() }

    //val totalSongs = SongDataProvider.getAllSongs()


    //var selectedSong: Song? = null

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()

        this.notificationManager = SongNotificationManager(this)
        this.refreshSongManager = RefreshSongManager(this)
        this.preferences = getSharedPreferences(DOTIFY_APP_PREF_KEY, Context.MODE_PRIVATE)

    }
}