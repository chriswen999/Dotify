package com.example.dotify.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val durationMillis: Long,
    val smallImageURL: Int,
    val largeImageURL: Int
) : Parcelable
