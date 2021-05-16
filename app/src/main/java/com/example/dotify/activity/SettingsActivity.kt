package com.example.dotify.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.dotify.R

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

fun startSetting(context: Context, imgURL: Int, title: String) = with(context) {
    val intent = Intent(context, SettingsActivity::class.java).apply {
        val bundle = Bundle().apply {
            putInt("imgURL", imgURL)
            putString("title", title)


        }
        putExtras(bundle)
    }

    startActivity(intent)
}


