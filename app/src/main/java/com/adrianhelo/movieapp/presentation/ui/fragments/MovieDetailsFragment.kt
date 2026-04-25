package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.adrianhelo.movieapp.databinding.MovieDetailsBinding
import com.adrianhelo.movieapp.presentation.viewmodel.MovieViewModel
import com.bumptech.glide.Glide
import kotlin.collections.ArrayList

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
            binding.titleMediaDetails.text = movieDetails?.mediaTitle
            binding.overviewMediaDetails.text = movieDetails?.mediaOverview
            binding.releaseDateMediaDetails.text = movieDetails?.mediaReleaseDate
            binding.runtimeMediaDetails.text = movieDetails?.mediaRuntime.toString()
            binding.taglineMediaDetails.text = movieDetails?.mediaTagline

            if (movieDetails != null) {
                val formattedGenre = movieDetails.mediaGenres.joinToString(", "){it.name}
                binding.genreMediaDetails.text = formattedGenre
            }

            val voteAverage = movieDetails?.mediaAverage
            val rating = (voteAverage?.div(2))?.toFloat()
            if (rating != null) {
                binding.movieRatingBar.rating = rating
            }

            val imageUrl = "https://image.tmdb.org/t/p/w500${movieDetails?.mediaPosterPath}"
            Glide.with(this).load(imageUrl).into(binding.imageMediaDetails)
        }
    }
}