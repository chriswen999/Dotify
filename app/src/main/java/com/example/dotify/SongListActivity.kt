package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import androidx.constraintlayout.widget.ConstraintLayout
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.databinding.SongListActivityBinding

fun navigateToSongListActivity(context: Context) = with(context) {
    val intent = Intent(this, SongListActivity::class.java)
    startActivity(intent)
}

private const val THE_SONG = "theSong"


class SongListActivity : AppCompatActivity() {
    private lateinit var binding: SongListActivityBinding
    //private var visible: Int = VISIBLE
    private var theSong : Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SongListActivityBinding.inflate(layoutInflater).apply { setContentView(root) }



        title = "All Songs"
        //val people = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        if(savedInstanceState != null){
            theSong = savedInstanceState.getParcelable(THE_SONG)
            with(binding) {
                    llMiniPlayer.visibility = VISIBLE
                    val params = rvPeople.layoutParams as ConstraintLayout.LayoutParams
                    params.bottomToTop = llMiniPlayer.id
                    rvPeople.requestLayout()

                    tvMiniPlayer.text = (theSong?.title + "-" + theSong?.artist)
                    llMiniPlayer.setOnClickListener {
                        theSong?.let { it1 -> navigateToPlayerDetailActivity(this@SongListActivity, it1) }
                    }
            }

        }


        with(binding) {
            val songs = SongDataProvider.getAllSongs()
            val adapter = SongListAdapter(songs)
            rvPeople.adapter = adapter

            adapter.onPersonClickListener = { song ->


                llMiniPlayer.visibility = VISIBLE
                val params = rvPeople.layoutParams as ConstraintLayout.LayoutParams
                params.bottomToTop = llMiniPlayer.id
                rvPeople.requestLayout()


                tvMiniPlayer.text = (song.title + "-" + song.artist)
                llMiniPlayer.setOnClickListener {
                    navigateToPlayerDetailActivity(this@SongListActivity, song)
                }
                theSong = song
            }

            btnShuffle.setOnClickListener{
                adapter.updatePeople(songs.toMutableList().shuffled())
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(THE_SONG, theSong)
        super.onSaveInstanceState(outState)
    }
}