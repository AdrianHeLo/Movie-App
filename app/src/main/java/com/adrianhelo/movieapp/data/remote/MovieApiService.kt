package com.adrianhelo.movieapp.data.remote

import com.adrianhelo.movieapp.data.model.MovieResponse
import com.adrianhelo.movieapp.data.model.SeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieResponse>
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key")apiKey: String): Response<MovieResponse>
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key")apiKey: String): Response<MovieResponse>
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key")apiKey: String): Response<MovieResponse>

    @GET("tv/popular")
    suspend fun getPopularSeries(@Query("api_key") apiKey: String): Response<SeriesResponse>
    @GET("tv/airing_today")
    suspend fun getAiringTodaySeries(@Query("api_key") apiKey: String): Response<SeriesResponse>
    @GET("tv/on_the_air")
    suspend fun getOnAirSeries(@Query("api_key") apiKey: String): Response<SeriesResponse>
    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(@Query("api_key") apiKey: String): Response<SeriesResponse>
}