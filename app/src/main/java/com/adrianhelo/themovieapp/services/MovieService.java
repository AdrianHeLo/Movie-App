package com.adrianhelo.themovieapp.services;

import com.adrianhelo.themovieapp.models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);
}
