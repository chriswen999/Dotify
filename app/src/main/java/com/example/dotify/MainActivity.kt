package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun nextClicked(view:View){
        Log.i("nc", "Next has been clicked")

        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }
    fun prevClicked(view:View){
        Log.i("nc", "Next has been clicked")

        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()

    }
}