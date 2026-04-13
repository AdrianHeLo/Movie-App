package com.adrianhelo.movieapp.data.repository

import com.adrianhelo.movieapp.data.remote.RetrofitInstance

class MoviesRepository {

        //Methods of Movies
        suspend fun getPopularMovies(apiKey: String) = RetrofitInstance.api.getPopularMovies(apiKey)
        suspend fun getUpcomingMovies(apiKey: String) = RetrofitInstance.api.getUpcomingMovies(apiKey)
        suspend fun getNowPlayingMovies(apiKey: String) = RetrofitInstance.api.getNowPlayingMovies(apiKey)
        suspend fun getTopRatedMovies(apiKey: String) = RetrofitInstance.api.getTopRatedMovies(apiKey)

        //Methods of Series
        suspend fun getPopularSeries(apiKey: String) = RetrofitInstance.api.getPopularSeries(apiKey)
        suspend fun getTopRatedSeries(apiKey: String) = RetrofitInstance.api.getTopRatedSeries(apiKey)
        suspend fun getOnAirSeries(apiKey: String) = RetrofitInstance.api.getOnAirSeries(apiKey)
        suspend fun getOnAiringSeries(apiKey: String) = RetrofitInstance.api.getAiringTodaySeries(apiKey)

        suspend fun searchMulti(apiKey: String, query: String) = RetrofitInstance.api.searchMulti(apiKey, query)

        suspend fun getMovieDetails(mediaId: Int, apiKey: String) = RetrofitInstance.api.getMovieDetails(mediaId, apiKey)
}