package com.example.dotify.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.DotifyApplication
import com.example.dotify.adapter.SongListAdapter
import com.example.dotify.databinding.SongListActivityBinding
import com.example.dotify.manager.SongManager
import com.example.dotify.model.Song
import kotlinx.coroutines.launch

fun navigateToSongListActivity(context: Context) = with(context) {
    val intent = Intent(this, SongListActivity::class.java)
    startActivity(intent)
}

private const val THE_SONG = "theSong"


class SongListActivity : AppCompatActivity() {
    private lateinit var binding: SongListActivityBinding
    private var theSong : Song? = null
    lateinit var songManager: SongManager

    private val dotifyApp: DotifyApplication by lazy { application as DotifyApplication }
    private val dataRepository by lazy { dotifyApp.dataRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SongListActivityBinding.inflate(layoutInflater).apply { setContentView(root) }

        this.songManager = dotifyApp.songManager

        val allSongs = listOf<Song>()



        with(binding) {
            //val songs = SongDataProvider.getAllSongs()
            val adapter = SongListAdapter(allSongs)
            rvPeople.adapter = adapter

            Log.i("next", "out")

            lifecycleScope.launch {
                Log.i("next", "inside")
                val songs = dataRepository.getSong()
                Log.i("next", "inside111")
                val newSongs = listOf(songs)
                Log.i("next", "$newSongs Next has been clicked")
                Log.i("next", "inside2222")

                adapter.updatePeople(newSongs)
            }

            adapter.onPersonClickListener = { song ->
                songManager.onSongSelected(song)


                llMiniPlayer.visibility = VISIBLE
                val params = rvPeople.layoutParams as ConstraintLayout.LayoutParams
                params.bottomToTop = llMiniPlayer.id
                rvPeople.requestLayout()


                tvMiniPlayer.text = (song.title + "-" + song.artist)
                llMiniPlayer.setOnClickListener {
                    navigateToPlayerDetailActivity(this@SongListActivity)
                }
                theSong = song

            }

            btnShuffle.setOnClickListener{
                adapter.updatePeople(allSongs.toMutableList().shuffled())
            }
        }



        if(savedInstanceState != null){
            theSong = savedInstanceState.getParcelable(THE_SONG)
            with(binding) {
                llMiniPlayer.visibility = VISIBLE
                val params = rvPeople.layoutParams as ConstraintLayout.LayoutParams
                params.bottomToTop = llMiniPlayer.id
                rvPeople.requestLayout()

                tvMiniPlayer.text = (theSong?.title + "-" + theSong?.artist)
                llMiniPlayer.setOnClickListener {
                    theSong?.let { it1 -> navigateToPlayerDetailActivity(this@SongListActivity) }
                }
            }

        }

        //val allSongs = dotifyApp.totalSongs



        title = "All Songs"


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(THE_SONG, theSong)
        super.onSaveInstanceState(outState)
    }
}