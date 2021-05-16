package com.example.dotify.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dotify.BuildConfig
import com.example.dotify.databinding.FragmentAboutBinding

//

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAboutBinding.inflate(inflater)

        with(binding) {
            val version = BuildConfig.VERSION_NAME
            tvVersionNumber.text = "$version"
        }

        return binding.root
    }
}