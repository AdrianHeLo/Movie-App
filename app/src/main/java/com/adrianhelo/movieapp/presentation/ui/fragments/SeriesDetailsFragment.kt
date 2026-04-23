package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.databinding.FragmentSeriesDetailsBinding
import com.adrianhelo.movieapp.presentation.viewmodel.SeriesViewModel
import com.bumptech.glide.Glide

class SeriesDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSeriesDetailsBinding
    private val seriesViewModel: SeriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSeriesDetailsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val movieId = arguments?.getInt("SERIE_ID") ?: 0
        val apiKey = "c58d486e39335c088f93249c7d26df58"
        seriesViewModel.getSeriesDetails(movieId, apiKey)
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        seriesViewModel.seriesDetails.observe(viewLifecycleOwner) { seriesDetails ->

            Log.d("SERIES_DETAILS", seriesDetails.toString())
            binding.nameSeriesDetails.text = seriesDetails?.seriesName
            binding.overviewSeriesDetails.text = seriesDetails?.seriesOverview
            binding.seasonsSeriesDetails.text = seriesDetails?.numberOfSeasons.toString()

            if (seriesDetails != null) {
                val listOfGenres = ArrayList<String>()
                for (i in 1 until seriesDetails.seriesGenres.size){
                    val item = seriesDetails.seriesGenres[i].name
                    listOfGenres.add(item)
                }
                binding.genreMediaDetails.text = listOfGenres.toString()
            }

            val voteAverage = seriesDetails?.seriesAverage
            val rating = (voteAverage?.div(2))?.toFloat()
            if (rating != null) {
                binding.seriesRatingBar.rating = rating
            }

            val imageUrl = "https://image.tmdb.org/t/p/w500${seriesDetails?.seriesPosterPath}"
            Glide.with(this).load(imageUrl).into(binding.imageSeriesDetails)
        }
    }
}