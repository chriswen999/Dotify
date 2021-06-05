package com.example.dotify

import android.app.Application
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.manager.SongManager
import com.example.dotify.manager.SongNotificationManager
import com.example.dotify.repository.DataRepository

//import com.example.dotify.model.Song

class DotifyApplication: Application() {

    var playCount: Int = 0
    lateinit var dataRepository: DataRepository
    lateinit var notificationManager: SongNotificationManager

    val  songManager: SongManager by lazy { SongManager() }

    //val totalSongs = SongDataProvider.getAllSongs()


    //var selectedSong: Song? = null

    override fun onCreate() {
        super.onCreate()

        dataRepository = DataRepository()

        this.notificationManager = SongNotificationManager(this)

    }
}