package com.example.dotify.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.edit

import androidx.lifecycle.lifecycleScope
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.DotifyApplication
import com.example.dotify.adapter.SongListAdapter
import com.example.dotify.databinding.SongListActivityBinding
import com.example.dotify.manager.SongManager
import com.example.dotify.manager.SongNotificationManager
import com.example.dotify.model.AllSongs
import com.example.dotify.model.Song
import kotlinx.coroutines.launch

fun navigateToSongListActivity(context: Context) = with(context) {
    val intent = Intent(this, SongListActivity::class.java)
    startActivity(intent)
}

private const val THE_SONG = "theSong"
const val NOTIFICATIONS_ENABLED_PREF_KEY = "NOTIFICATIONS_ENABLED"

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: SongListActivityBinding
    private var theSong : Song? = null
    lateinit var songManager: SongManager
    //private lateinit var adapter: SongListAdapter
    private lateinit var newSongs: List<Song>


    private val dotifyApp: DotifyApplication by lazy { application as DotifyApplication }
    private val dataRepository by lazy { dotifyApp.dataRepository }
    private val songNotificationManager: SongNotificationManager by lazy { dotifyApp.notificationManager }

    private val refreshSongManager by lazy {dotifyApp.refreshSongManager}

    private val preferences by lazy { dotifyApp.preferences }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("next", "haha")
        super.onCreate(savedInstanceState)
        binding = SongListActivityBinding.inflate(layoutInflater).apply { setContentView(root) }



        Log.i("next", "inside")



        with(binding) {
            switchNotification.isChecked =  preferences.getBoolean(NOTIFICATIONS_ENABLED_PREF_KEY, false)
            songNotificationManager.notificationEnabled = preferences.getBoolean(NOTIFICATIONS_ENABLED_PREF_KEY, false)

            switchNotification.setOnCheckedChangeListener { _, isChecked ->
                songNotificationManager.notificationEnabled = switchNotification.isChecked
                preferences.edit { putBoolean(NOTIFICATIONS_ENABLED_PREF_KEY, isChecked) }

                if (isChecked) {
                    refreshSongManager.startRefreshSongsPeriodically()
                }
            }

            newSongs = listOf()

            val adapter = SongListAdapter(newSongs)
            rvPeople.adapter = adapter
            songManager = dotifyApp.songManager
            lifecycleScope.launch {

                val allsongs: AllSongs = dataRepository.getAllSongs()

                newSongs = allsongs.songs

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
                adapter.updatePeople(newSongs.toMutableList().shuffled())
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