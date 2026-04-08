package com.adrianhelo.movieapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrianhelo.movieapp.data.model.Movie
import com.adrianhelo.movieapp.data.repository.MoviesRepository
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    private val repository = MoviesRepository()

    private val _movies  = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _searchMulti  = MutableLiveData<List<Movie>>()
    val searchMulti: LiveData<List<Movie>> = _searchMulti

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

    fun searchMulti(apiKey: String, query: String){
        if(query.isEmpty()) return
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.searchMulti(apiKey, query)
                if (response.isSuccessful){
                    _searchMulti.postValue(response.body()?.results ?: emptyList())
                }
            } catch (e: Exception){
                Log.e("MainActivity", e.message.toString())
            }finally {
                _isLoading.value = false
            }
        }
    }

}