package com.example.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {

    private val safeArgs: StatisticsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentStatisticsBinding.inflate(inflater)

        with(binding) {
            ivAlbumPic.setImageResource(safeArgs.imgURL)
            val title = safeArgs.title
            val songPlayCount = safeArgs.playCount
            tvPlayCount.text = "$title has been Played: $songPlayCount times"
        }

        return binding.root
    }
}