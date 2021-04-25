package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.ericchee.songdataprovider.Song
import com.example.dotify.databinding.ActivityPlayerDetailBinding
import kotlin.random.Random

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
    private val randomNumber = Random.nextInt(1000, 50000)
    private lateinit var tvPlayText : TextView
    private var playNum : Int = 0
    private var cu : Boolean = true;
    private lateinit var binding: ActivityPlayerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerDetailBinding.inflate(layoutInflater).apply{setContentView(root)}
        with(binding){
            val song: Song? = intent.getParcelableExtra<Song>("theSong")
            if (song?.largeImageID != null) {
                albumPic.setImageResource(song.largeImageID)
            }
            songName.text = song?.title.toString()
            artistName.text = song?.artist.toString()
        }

        playNum = randomNumber;
        tvPlayText = findViewById<TextView>(R.id.tvPlayNum)

        tvPlayText.text = "$playNum plays"


        val inputtedText = findViewById<TextView>(R.id.userInputtedUserName);
        inputtedText.setVisibility(View.GONE);
    }

    fun cuClicked(view: View){
        Log.i("cu", "Change User has been clicked")

        val userInputtedUserName = findViewById<TextView>(R.id.userInputtedUserName)
        val userName = findViewById<TextView>(R.id.userName)
        val buttonName = findViewById<TextView>(R.id.cuButton)
        var userInputtedText = userInputtedUserName.text.toString()
        if(cu){
            userInputtedUserName.setVisibility(View.VISIBLE);
            userName.setVisibility(View.GONE);
            buttonName.text = "Apply"
            userInputtedUserName.setText("")
            cu = false;
        } else {
            userInputtedUserName.setVisibility(View.GONE);
            userName.setVisibility(View.VISIBLE);
            if(userInputtedText != "") {
                userName.text = userInputtedText
            }
            buttonName.text = "Change User"
            cu = true;
        }
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