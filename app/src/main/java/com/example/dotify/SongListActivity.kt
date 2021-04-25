package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import androidx.constraintlayout.widget.ConstraintLayout
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.databinding.SongListActivityBinding

fun navigateToSongListActivity(context: Context) = with(context) {
    val intent = Intent(this, SongListActivity::class.java)
    startActivity(intent)
}

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: SongListActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SongListActivityBinding.inflate(layoutInflater).apply { setContentView(root) }



        title = "All Songs"
        //val people = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
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
            }

            btnShuffle.setOnClickListener{
                adapter.updatePeople(songs.toMutableList().shuffled())
            }
        }
    }
}