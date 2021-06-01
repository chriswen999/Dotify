package com.example.dotify.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.dotify.DotifyApplication

import com.example.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {

    private val safeArgs: StatisticsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentStatisticsBinding.inflate(inflater)

        with(binding) {

            val imgSource = safeArgs.imgURL
            ivAlbumPic.load(imgSource)
            val title = safeArgs.title
            val songPlayCount = dotifyApp.playCount
            tvPlayCount.text = "$title has been Played: $songPlayCount times"
        }

        return binding.root
    }

    lateinit var dotifyApp: DotifyApplication

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dotifyApp = context.applicationContext as DotifyApplication

    }
}