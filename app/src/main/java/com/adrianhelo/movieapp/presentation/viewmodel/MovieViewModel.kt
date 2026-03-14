package com.adrianhelo.movieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrianhelo.movieapp.data.model.Movie
import com.adrianhelo.movieapp.data.model.Series
import com.adrianhelo.movieapp.data.repository.MoviesRepository
import kotlinx.coroutines.launch
import retrofit2.http.Query

class MovieViewModel: ViewModel() {

    private val repository = MoviesRepository()
    private val _movies  = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    private val _searchMovies  = MutableLiveData<List<Movie>>()
    val searchMovies: LiveData<List<Movie>> = _searchMovies
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

    fun getMovie(apiKey: String, query: String){
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getMovie(apiKey, query)
            if (response.isSuccessful){
                _searchMovies.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }
}