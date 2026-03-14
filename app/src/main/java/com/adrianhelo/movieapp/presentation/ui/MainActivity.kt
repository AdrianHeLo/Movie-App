package com.adrianhelo.movieapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.presentation.adapter.MovieAdapter
import com.adrianhelo.movieapp.presentation.ui.fragments.AboutFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.PopularFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.PlayingFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.RatedFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.SettingsFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.UpcomingFragment
import com.adrianhelo.movieapp.presentation.viewmodel.MovieViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView
    private lateinit var recyclerView: RecyclerView

    private val movieViewModel: MovieViewModel by viewModels()
    private var movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bundle = Bundle()

        drawerLayout = findViewById(R.id.drawer_nav_view)
        toolbar = findViewById(R.id.toolbar)
        navView = findViewById(R.id.nav_view_container)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId){
                R.id.popular_movie_nav_menu -> {
                    loadFragment(PopularFragment(), it.title.toString())
                }
                R.id.playing_movie_nav_menu -> {
                    loadFragment(PlayingFragment(), it.title.toString())
                }
                R.id.upcoming_movie_nav_menu -> {
                    loadFragment(UpcomingFragment(), it.title.toString())
                }
                R.id.rated_movie_nav_menu -> {
                    loadFragment(RatedFragment(), it.title.toString())
                }
                R.id.popular_series_nav_menu -> {
                    bundle.putString("Query", "Series")
                    replaceFragment(PopularFragment(), bundle, it.title.toString())
                }
                R.id.rated_series_nav_menu -> {
                    bundle.putString("Query", "Series")
                    replaceFragment(RatedFragment(), bundle, it.title.toString())
                }
                R.id.playing_series_nav_menu -> {
                    bundle.putString("Query", "Series")
                    replaceFragment(PlayingFragment(), bundle, it.title.toString())
                }
                R.id.upcoming_series_nav_menu -> {
                    bundle.putString("Query", "Series")
                    replaceFragment(UpcomingFragment(), bundle, it.title.toString())
                }
                R.id.about_options_movie_nav_menu -> {
                    loadFragment(AboutFragment(), it.title.toString())
                }
                R.id.settings_options_movie_nav_menu -> {
                    loadFragment(SettingsFragment(), it.title.toString())
                }
            }
            true
        }
        loadFragment(PopularFragment(), "Home")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.isSubmitButtonEnabled = false
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    private fun replaceFragment(fragment: Fragment, bundle: Bundle, name: String){
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        drawerLayout.closeDrawers()
        title = name
    }

    private fun loadFragment(fragment: Fragment, name: String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        title = name
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null){
            recyclerView = findViewById(R.id.recycler_container)
            recyclerView.adapter = movieAdapter
            movieViewModel.searchMovies.observe(this){
                movieAdapter.submitList(it)
            }
            movieViewModel.getMovie(getString(R.string.api_key) , newText)
            Log.i("MainActivity", movieViewModel.getMovie(getString(R.string.api_key) , newText).toString())
            Log.i("MainActivity", movieViewModel.searchMovies.toString())
        }
        return true
    }
}