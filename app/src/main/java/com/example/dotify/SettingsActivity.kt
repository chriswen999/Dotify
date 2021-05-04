package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class SettingsActivity : AppCompatActivity() {



    private val navController by lazy { findNavController(R.id.navHost) }

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        navController.setGraph(R.navigation.nav_graph, intent.extras)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupActionBarWithNavController(navController)


    }
    override fun onSupportNavigateUp() = navController.navigateUp()
}

fun startSetting(context: Context, imgURL: Int, title: String, playCount: String) = with(context) {
    val intent = Intent(context, SettingsActivity::class.java).apply {
        val bundle = Bundle().apply {
            putInt("imgURL", imgURL)
            putString("title", title)
            putString("playCount", playCount)

        }
        putExtras(bundle)
    }

    startActivity(intent)
}


