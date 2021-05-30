package com.example.dotify.repository

import com.example.dotify.model.AllSongs
import com.example.dotify.model.Song
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



class DataRepository {

    private val songService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SongService::class.java)


    //suspend fun getSong(): Song = songService.getSong()

    suspend fun getAllSongs() = songService.getAllSongs()


}

interface SongService {



    @GET("echeeUW/codesnippets/master/musiclibrary.json")
    suspend fun getAllSongs(): AllSongs




}