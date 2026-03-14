package com.adrianhelo.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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

class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var swipeToRefreshLayout: SwipeRefreshLayout
    private val movieViewModel: MovieViewModel by viewModels()
    private val seriesViewModel: SeriesViewModel by viewModels()
    private val movieAdapter = MovieAdapter()
    private val seriesAdapter = SeriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        var bundle = arguments?.getString("Query")

        if (bundle != null){
            displaySeriesView()
            seriesViewModel.series.observe(viewLifecycleOwner){
                seriesAdapter.submitList(it)
            }
            seriesViewModel.getPopularSeries(getString(R.string.api_key))
        }else{
            displayMoviesView()
            movieViewModel.movies.observe(viewLifecycleOwner){
                movieAdapter.submitList(it)
            }
            movieViewModel.getPopularMovies(getString(R.string.api_key))
        }


        swipeToRefreshLayout = binding.popularSwipeRefreshContainer
        movieViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeToRefreshLayout.isRefreshing = isLoading
        }
        seriesViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeToRefreshLayout.isRefreshing = isLoading
        }

        swipeToRefreshLayout.setOnRefreshListener{
            if (bundle != null){
                seriesViewModel.getPopularSeries(getString(R.string.api_key))
            }else{
                movieViewModel.getPopularMovies(getString(R.string.api_key))
            }
        }

        return binding.root
    }

    private fun displayMoviesView() {
        binding.popularFragmentRecyclerView.adapter = movieAdapter
        binding.popularFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))
    }

    private fun displaySeriesView() {
        binding.popularFragmentRecyclerView.adapter = seriesAdapter
        binding.popularFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))
    }
}