package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.databinding.FragmentPopularBinding
import com.adrianhelo.movieapp.presentation.adapter.MovieAdapter
import com.adrianhelo.movieapp.presentation.adapter.SeriesAdapter
import com.adrianhelo.movieapp.presentation.viewmodel.MovieViewModel
import com.adrianhelo.movieapp.presentation.viewmodel.SeriesViewModel
import kotlin.concurrent.thread

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var swipeToRefreshLayout: SwipeRefreshLayout
    private val movieViewModel: MovieViewModel by viewModels()
    private val seriesViewModel: SeriesViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var seriesAdapter: SeriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        swipeToRefreshLayout = binding.popularSwipeRefreshContainer

        val queryType = arguments?.getString("Query")

        movieAdapter = MovieAdapter{ movieId ->
            getMovieId(movieId)
        }

        seriesAdapter = SeriesAdapter { seriesId ->
            getSeriesId(seriesId)
        }

        if (queryType != null){
            setupSeriesObserver()
        }else{
            setupMoviesObserver()
        }

        setupSwipeRefresh(queryType)
        return binding.root
    }

    private fun setupMoviesObserver() {
        binding.popularFragmentRecyclerView.adapter = movieAdapter
        binding.popularFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))

        movieViewModel.movies.observe(viewLifecycleOwner){
            movieAdapter.submitList(it)
        }
        movieViewModel.getPopularMovies(getString(R.string.api_key))

        movieViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeToRefreshLayout.isRefreshing = isLoading
        }
    }

    private fun setupSeriesObserver() {
        binding.popularFragmentRecyclerView.adapter = seriesAdapter
        binding.popularFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))

        seriesViewModel.series.observe(viewLifecycleOwner){
            seriesAdapter.submitList(it)
        }
        seriesViewModel.getPopularSeries(getString(R.string.api_key))

        seriesViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeToRefreshLayout.isRefreshing = isLoading
        }
    }

    private fun setupSwipeRefresh(queryType: String?) {
        binding.popularSwipeRefreshContainer.setOnRefreshListener {
            if (queryType == "Series") {
                seriesViewModel.getPopularSeries(getString(R.string.api_key))
            } else {
                movieViewModel.getPopularMovies(getString(R.string.api_key))
            }
        }
    }

    private fun getMovieId(movieID: Int){
        val id = Bundle()
        id.putInt("MOVIE_ID", movieID)
        val detailFragment = MovieDetailsFragment()
        detailFragment.arguments = id
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun getSeriesId(seriesId: Int) {
        val id = Bundle()
        id.putInt("SERIE_ID", seriesId)
        val detailFragment = SeriesDetailsFragment()
        detailFragment.arguments = id
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}