package com.adrianhelo.themovieapp;

import android.os.Bundle;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.adrianhelo.themovieapp.adapters.MovieAdapter;
import com.adrianhelo.themovieapp.databinding.ActivityMainBinding;
import com.adrianhelo.themovieapp.models.Movie;
import com.adrianhelo.themovieapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movieArrayList;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private ActivityMainBinding binding;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        recyclerView = findViewById(R.id.recycler_view);

        getPopularMovies();

        swipeRefreshLayout = binding.swipeLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });

    }

    private void getPopularMovies() {
        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesLiveData) {
                movieArrayList = (ArrayList<Movie>) moviesLiveData;
                displayMoviesInRecyclerView();
            }
        });
    }

    private void displayMoviesInRecyclerView() {
        recyclerView = binding.recyclerView;
        movieAdapter = new MovieAdapter(movieArrayList,this );
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter.notifyDataSetChanged();
    }
}