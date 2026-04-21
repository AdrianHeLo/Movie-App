package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.databinding.FragmentRatedBinding
import com.adrianhelo.movieapp.presentation.adapter.MovieAdapter
import com.adrianhelo.movieapp.presentation.adapter.SeriesAdapter
import com.adrianhelo.movieapp.presentation.viewmodel.MovieViewModel
import com.adrianhelo.movieapp.presentation.viewmodel.SeriesViewModel

class RatedFragment : Fragment() {

    private lateinit var binding: FragmentRatedBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val movieViewModel: MovieViewModel by viewModels()
    private val seriesViewModel: SeriesViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private val seriesAdapter = SeriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRatedBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val bundle = arguments?.getString("Query")

        movieAdapter = MovieAdapter{ movieId ->
            Toast.makeText(context, "Movie ID: $movieId", Toast.LENGTH_LONG).show()
        }

        if (bundle != null){
            displaySeriesView()
            seriesViewModel.series.observe(viewLifecycleOwner){
                seriesAdapter.submitList(it)
            }
            seriesViewModel.getTopRatedSeries(getString(R.string.api_key))
        }else{
            displayMoviesView()
            movieViewModel.movies.observe(viewLifecycleOwner){
                movieAdapter.submitList(it)
            }
            movieViewModel.getTopRatedMovies(getString(R.string.api_key))
        }

        swipeRefreshLayout = binding.ratedSwipeRefreshContainer
        movieViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeRefreshLayout.isRefreshing = isLoading
        }
        seriesViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeRefreshLayout.isRefreshing = isLoading
        }
        swipeRefreshLayout.setOnRefreshListener {
            if (bundle != null){
                seriesViewModel.getTopRatedSeries(getString(R.string.api_key))
            }else{
                movieViewModel.getTopRatedMovies(getString(R.string.api_key))
            }
        }

        return binding.root
    }

    private fun displayMoviesView() {
        binding.ratedFragmentRecyclerView.adapter = movieAdapter
        binding.ratedFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))
    }

    private fun displaySeriesView() {
        binding.ratedFragmentRecyclerView.adapter = seriesAdapter
        binding.ratedFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))
    }
}