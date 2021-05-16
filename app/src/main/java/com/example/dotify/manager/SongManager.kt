package com.example.dotify.manager

import com.example.dotify.model.Song


class SongManager {
    var selectedSong: Song? = null
        private set

    fun onSongSelected(song: Song) {
        selectedSong = song;
    }
}