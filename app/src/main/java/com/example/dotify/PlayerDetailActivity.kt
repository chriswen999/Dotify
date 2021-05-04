package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.example.dotify.databinding.ActivityPlayerDetailBinding
import kotlin.random.Random

private const val COUNT_VALUE_KEY = "COUNT_VALUE_KEY"

fun navigateToPlayerDetailActivity(context: Context, song: Song){
    val intent = Intent(context, PlayerDetailActivity::class.java).apply{
        val bundle = Bundle().apply {
            putParcelable("theSong", song)
        }
        putExtras(bundle)
    }
    context.startActivity( intent )
}

class PlayerDetailActivity : AppCompatActivity() {
    private var playNum = Random.nextInt(1000, 50000)
    private lateinit var tvPlayText : TextView
    //private var playNum : Int = 0
    private lateinit var binding: ActivityPlayerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerDetailBinding.inflate(layoutInflater).apply{setContentView(root)}
        val view = binding.root
        with(binding) {
            val song: Song? = intent.getParcelableExtra<Song>("theSong")
            if(savedInstanceState != null) {
                playNum = savedInstanceState.getInt(COUNT_VALUE_KEY, 0)
            }
            if (song?.largeImageID != null) {
                albumPic.setImageResource(song.largeImageID)
            }
            if (song != null) {
                songName.text = song?.title.toString()
                artistName.text = song?.artist.toString()

            }
            btnSetting.setOnClickListener {

                if (song != null) {
                    startSetting(
                        this@PlayerDetailActivity,
                        song.largeImageID,
                        song.title,

                        playNum.toString()
                    )
                }


            }
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        //playNum = randomNumber;
        tvPlayText = findViewById<TextView>(R.id.tvPlayNum)

        tvPlayText.text = "$playNum plays"




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu_items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.searchItem -> Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(COUNT_VALUE_KEY, playNum)
        super.onSaveInstanceState(outState)
    }



    fun playClick(view: View){
        playNum = playNum + 1;
        tvPlayText.text = "$playNum plays"
    }

    fun nextClick(view:View){
        Log.i("next", "Next has been clicked")

        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }


    fun prevClick(view: View) {
        Log.i("prev", "prev has been clicked")

        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }
}