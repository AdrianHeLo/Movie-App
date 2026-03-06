package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.databinding.FragmentAboutBinding
import com.bumptech.glide.Glide

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(inflater, container, false)

        val linkTmdb = binding.tmdbAttributesFragmentAbout.text
        var linkGit = binding.versionFragmentAbout.text



        return binding.root
    }
}