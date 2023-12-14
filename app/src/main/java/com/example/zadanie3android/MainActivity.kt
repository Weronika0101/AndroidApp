package com.example.zadanie3android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.zadanie3android.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var sharedViewModel: SharedViewModel
    val myvm : MyViewModel by viewModels {MyViewModel.Factory}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.firstFragment,
                R.id.listFragment,
                R.id.swipeFragment,
                R.id.fragmentDetails,
                R.id.fragmentAdd,
                R.id.fragment_new
            ),
            drawerLayout
        )

        // Inicjalizacja ActionBarDrawerToggle
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        // Ustawienie Listenera do kontrolowania stanu Navigation Drawer
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.sfcontainer) as NavHostFragment
        val navController = navHostFragment.navController

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)

    }
}