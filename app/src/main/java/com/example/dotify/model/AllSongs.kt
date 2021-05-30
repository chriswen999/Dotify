package com.example.dotify.model

data class AllSongs(
    val title: String,
    val numOfSongs: Integer,
    val songs: List<Song>
)
