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
import com.adrianhelo.movieapp.data.model.Movie
import com.adrianhelo.movieapp.databinding.FragmentPlayingBinding
import com.adrianhelo.movieapp.presentation.adapter.MovieAdapter
import com.adrianhelo.movieapp.presentation.adapter.SeriesAdapter
import com.adrianhelo.movieapp.presentation.viewmodel.MovieViewModel
import com.adrianhelo.movieapp.presentation.viewmodel.SeriesViewModel

class PlayingFragment : Fragment() {

    private lateinit var binding: FragmentPlayingBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val movieViewModel: MovieViewModel by viewModels()
    private val seriesViewModel: SeriesViewModel by viewModels()

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var seriesAdapter: SeriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentPlayingBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        swipeRefreshLayout = binding.playingSwipeRefreshContainer

        val queryType = arguments?.getString("Query")

        movieAdapter = MovieAdapter{ movieId ->
            getMovieId(movieId)
        }

        seriesAdapter = SeriesAdapter { seriesId ->
            Toast.makeText(context, "SeriesId: $seriesId", Toast.LENGTH_LONG).show()
            //getSeriesId(seriesId)
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
        binding.playingFragmentRecyclerView.adapter = movieAdapter
        binding.playingFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))

        movieViewModel.movies.observe(viewLifecycleOwner){
            movieAdapter.submitList(it)
        }
        movieViewModel.getNowPlayingMovies(getString(R.string.api_key))

        movieViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeRefreshLayout.isRefreshing = isLoading
        }
    }

    private fun setupSeriesObserver() {
        binding.playingFragmentRecyclerView.adapter = seriesAdapter
        binding.playingFragmentRecyclerView.setLayoutManager(GridLayoutManager(requireContext(), 2))

        seriesViewModel.series.observe(viewLifecycleOwner){
            seriesAdapter.submitList(it)
        }
        seriesViewModel.getOnAiringSeries(getString(R.string.api_key))

        seriesViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            swipeRefreshLayout.isRefreshing = isLoading
        }
    }

    private fun setupSwipeRefresh(queryType: String?) {
        binding.playingSwipeRefreshContainer.setOnRefreshListener{
            if (queryType == "Series"){
                seriesViewModel.getOnAiringSeries(getString(R.string.api_key))
            }else{
                movieViewModel.getNowPlayingMovies(getString(R.string.api_key))
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
}