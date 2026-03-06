package com.adrianhelo.movieapp.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.adrianhelo.movieapp.R
import com.adrianhelo.movieapp.presentation.ui.fragments.AboutFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.PopularFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.PlayingFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.RatedFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.SettingsFragment
import com.adrianhelo.movieapp.presentation.ui.fragments.UpcomingFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

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

    private fun loadFragment(fragment: Fragment, tittle: String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        title = tittle
    }
}