package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.data.model.MovieDetails
import com.adrianhelo.movieapp.databinding.MovieDetailsBinding
import com.adrianhelo.movieapp.presentation.adapter.MovieDetailsAdapter
import com.adrianhelo.movieapp.presentation.viewmodel.MovieViewModel
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: MovieDetailsBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = MovieDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val movieId = arguments?.getInt("MOVIE_ID") ?: 0
        val apiKey = "c58d486e39335c088f93249c7d26df58"
        viewModel.getMovieDetails(movieId, apiKey)
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        // Observamos el objeto MovieDetails que viene del ViewModel
        viewModel.movieDetails.observe(viewLifecycleOwner) { movieDetails ->

            Log.d("MOVIE_DETAILS", movieDetails.toString())
            // AQUÍ ESTÁ EL TRUCO: Pasamos el objeto directamente a la variable del XML
            binding.titleMediaDetails.text = movieDetails?.mediaTitle
            binding.overviewMediaDetails.text = movieDetails?.mediaOverview
            binding.voteAverageMediaDetails.text = movieDetails?.mediaAverage.toString()
            binding.releaseDateMediaDetails.text = movieDetails?.mediaReleaseDate
            binding.runtimeMediaDetails.text = movieDetails?.mediaRuntime.toString()
            binding.taglineMediaDetails.text = movieDetails?.mediaTagline

            val imageUrl = "https://image.tmdb.org/t/p/w500${movieDetails?.mediaPosterPath}"
            Glide.with(this).load(imageUrl).into(binding.imageMediaDetails)

            // Si tu objeto movieDetails ya trae la lista de géneros:
            /*
            if (movieDetails.genres.isNotEmpty()) {
                binding.gengres = movieDetails.genres[0] // Pasamos el primer género
            }
            */
        }
    }
}