package com.adrianhelo.movieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrianhelo.movieapp.data.model.Movie
import com.adrianhelo.movieapp.data.model.Series
import com.adrianhelo.movieapp.data.repository.MoviesRepository
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    private val repository = MoviesRepository()
    private val _movies  = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    private val _series  = MutableLiveData<List<Series>>()
    val series: LiveData<List<Series>> = _series
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getPopularMovies(apiKey: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getPopularMovies(apiKey)
            if (response.isSuccessful){
                _movies.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getUpcomingMovies(apiKey: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getUpcomingMovies(apiKey)
            if (response.isSuccessful){
                _movies.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getTopRatedMovies(apiKey: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getTopRatedMovies(apiKey)
            if (response.isSuccessful){
                _movies.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getNowPlayingMovies(apiKey: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getNowPlayingMovies(apiKey)
            if (response.isSuccessful){
                _movies.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getPopularSeries(apiKey: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getPopularSeries(apiKey)
            if (response.isSuccessful){
                _series.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getTopRatedSeries(apiKey: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getTopRatedSeries(apiKey)
            if (response.isSuccessful){
                _series.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getOnAirSeries(apiKey: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getOnAirSeries(apiKey)
            if (response.isSuccessful){
                _series.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getOnAiringSeries(apiKey: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getOnAiringSeries(apiKey)
            if (response.isSuccessful){
                _series.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }
}