package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.databinding.MovieDetailsBinding
import com.adrianhelo.movieapp.presentation.adapter.MovieDetailsAdapter
import com.adrianhelo.movieapp.presentation.viewmodel.MovieViewModel

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: MovieDetailsBinding
    private val viewModel: MovieViewModel by viewModels()
    private val adapter = MovieDetailsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = MovieDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner


        return binding.root
    }

}