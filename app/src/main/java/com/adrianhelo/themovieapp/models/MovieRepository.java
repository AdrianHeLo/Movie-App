package com.adrianhelo.themovieapp.models;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.adrianhelo.themovieapp.R;
import com.adrianhelo.themovieapp.services.MovieService;
import com.adrianhelo.themovieapp.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData(){

        MovieService movieService = RetrofitInstance.getService();
        Log.i("MovieRepository", movieService.toString());
        Call<Result> call = movieService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        Log.i("MovieRepository", call.toString());

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Result result = response.body();
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);
                }else {
                    Toast.makeText(application, "Invalid API key: You must be granted a valid key.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("MovieRepository", "Network error", t);
                Toast.makeText(application, "Network error", Toast.LENGTH_LONG).show();
            }
        });
        return mutableLiveData;
    }
}
