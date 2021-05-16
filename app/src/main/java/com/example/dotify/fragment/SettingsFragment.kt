package com.example.dotify.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.dotify.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private val navController by lazy { findNavController() }

    private val safeArgs: SettingsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentSettingsBinding.inflate(inflater)
        val imgURL = safeArgs.imgURL
        val title = safeArgs.title

        with(binding) {
            btnProfile.setOnClickListener {
                navController.navigate(SettingsFragmentDirections.actionSettingFragmentToProfileFragment())
            }
            btnAbout.setOnClickListener {
                navController.navigate(SettingsFragmentDirections.actionSettingFragmentToAboutFragment())
            }
            btnStats.setOnClickListener {
                navController.navigate(SettingsFragmentDirections.actionSettingFragmentToStatisticsFragment(imgURL, title))
            }
        }

        return binding.root
    }
}