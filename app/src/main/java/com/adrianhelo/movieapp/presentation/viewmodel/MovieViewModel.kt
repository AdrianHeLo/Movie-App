package com.adrianhelo.movieapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrianhelo.movieapp.data.model.Genres
import com.adrianhelo.movieapp.data.model.Movie
import com.adrianhelo.movieapp.data.model.MovieDetails
import com.adrianhelo.movieapp.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {

    private val repository = MoviesRepository()

    private val _movieDetails = MutableLiveData<MovieDetails?>()
    val movieDetails: LiveData<MovieDetails?> = _movieDetails

    private val _genres = MutableLiveData<List<Genres>>()
    val genres: LiveData<List<Genres>> = _genres

    private val _movies  = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getPopularMovies(apiKey: String){
        Log.d("API_CHECK", "Enviando a Repo -> Key: '$apiKey'")
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
        viewModelScope.launch{
            _isLoading.value = true
            val response = repository.getNowPlayingMovies(apiKey)
            if (response.isSuccessful){
                _movies.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getMovieDetails(movieId: Int, apiKey: String){
        Log.d("API_CHECK", "Enviando a Repo -> ID: $movieId, Key: '$apiKey'")
        viewModelScope.launch(Dispatchers.IO){
            try {
                _isLoading.postValue(true)
                val response = repository.getMovieDetails(movieId, apiKey)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null){
                        _movieDetails.postValue(body)
                        Log.i("API_DEBUG", "Carga exitosa: ${body.mediaTitle}")
                        // Si MovieDetails contiene la lista de géneros:
                        // _genres.postValue(body.genres)
                    }else{
                        Log.e("API_DEBUG", "Respuesta exitosa pero body nulo")
                    }
                }else {
                    Log.e("API_DEBUG", "Error en API: ${response.code()} - ${response.errorBody()?.string()}")
                }
            }catch (e:Exception){
                Log.e("MOVIE_VIEWMODEL", "Fallo de red o conversión: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}