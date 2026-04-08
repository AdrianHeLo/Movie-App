package com.adrianhelo.movieapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrianhelo.movieapp.data.model.Media
import com.adrianhelo.movieapp.data.model.Movie
import com.adrianhelo.movieapp.data.repository.MoviesRepository
import kotlinx.coroutines.launch

class MediaViewModel: ViewModel() {

    private val repository = MoviesRepository()

    private val _searchMulti  = MutableLiveData<List<Media>>()
    val searchMulti: LiveData<List<Media>> = _searchMulti

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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
                Log.e("MediaViewModel", e.message.toString())
            }finally {
                _isLoading.value = false
            }
        }
    }
}