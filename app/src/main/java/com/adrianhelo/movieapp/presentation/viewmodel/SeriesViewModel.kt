package com.adrianhelo.movieapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrianhelo.movieapp.data.model.Movie
import com.adrianhelo.movieapp.data.model.Series
import com.adrianhelo.movieapp.data.model.SeriesDetails
import com.adrianhelo.movieapp.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesViewModel: ViewModel() {
    private val repository = MoviesRepository()

    private val _series = MutableLiveData<List<Series>>()
    val series: LiveData<List<Series>> = _series

    private val _seriesDetails = MutableLiveData<SeriesDetails>()
    val seriesDetails: LiveData<SeriesDetails?> = _seriesDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getPopularSeries(apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getPopularSeries(apiKey)
            if (response.isSuccessful) {
                _series.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getTopRatedSeries(apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getTopRatedSeries(apiKey)
            if (response.isSuccessful) {
                _series.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getOnAirSeries(apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getOnAirSeries(apiKey)
            if (response.isSuccessful) {
                _series.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getOnAiringSeries(apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getOnAiringSeries(apiKey)
            if (response.isSuccessful) {
                _series.value = response.body()?.results
            }
            _isLoading.value = false
        }
    }

    fun getSeriesDetails(seriesId: Int, apiKey: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getSeriesDetails(seriesId, apiKey)
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        _seriesDetails.postValue(body)
                        Log.i("API_DEBUG", "Carga exitosa: ${body.seriesName}")
                    }else{
                        Log.e("API_DEBUG", "Respuesta exitosa pero body nulo")
                    }
                }else{
                    Log.e("API_DEBUG", "Error en API: ${response.code()} - ${response.errorBody()?.string()}")
                }
            }catch (e:Exception){
                Log.e("SERIES_VIEWMODEL", "Falló de red o conversión: ${e.message}")
            }
        }
    }
}