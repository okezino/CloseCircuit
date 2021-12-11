package com.example.closedcircuitapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.closedcircuitapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var bottomAppBar: BottomAppBar
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBarDashboard.contentMain.bottomNavigationView.background = null
        setSupportActionBar(binding.appBarDashboard.dashboardActivityToolbar)

        val drawerLayout = binding.drawerLayout
        navController = findNavController(R.id.nav_host_fragment_content_main)
        bottomAppBar = binding.appBarDashboard.contentMain.bottomAppBar
        bottomNavigationView = binding.appBarDashboard.contentMain.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        onDestinationChangedListener()

    }

    private fun onDestinationChangedListener() {
        try {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomAppBar.visibility = View.VISIBLE
                binding.appBarDashboard.contentMain.fab.visibility = View.VISIBLE
                binding.appBarDashboard.appBarLayout.visibility = View.VISIBLE
                binding.appBarDashboard.appBarLayout.visibility = View.VISIBLE
                when (destination.id) {
                    R.id.dashboardFragment -> {
                        bottomAppBar.visibility = View.VISIBLE
                        binding.appBarDashboard.contentMain.fab.visibility = View.VISIBLE
                        binding.appBarDashboard.notificationImageView.visibility = View.VISIBLE
                        binding.appBarDashboard.profileImageView.visibility = View.VISIBLE
                        binding.appBarDashboard.appBarLayout.visibility = View.VISIBLE
                    }
                    R.id.dashboardFragment2 -> {
                        bottomAppBar.visibility = View.VISIBLE
                        binding.appBarDashboard.contentMain.fab.visibility = View.VISIBLE
                        binding.appBarDashboard.notificationImageView.visibility = View.VISIBLE
                        binding.appBarDashboard.profileImageView.visibility = View.VISIBLE
                    }
                    R.id.welcomeScreenFragment ->{
                        binding.appBarDashboard.contentMain.fab.visibility = View.GONE
                        bottomAppBar.visibility = View.INVISIBLE
                        binding.appBarDashboard.appBarLayout.visibility = View.INVISIBLE
                        binding.appBarDashboard.notificationImageView.visibility = View.GONE
                        binding.appBarDashboard.profileImageView.visibility = View.GONE

                    }

                    else -> {
                        binding.appBarDashboard.contentMain.fab.visibility = View.INVISIBLE
                        bottomAppBar.visibility = View.INVISIBLE
                        binding.appBarDashboard.appBarLayout.visibility = View.VISIBLE
                        binding.appBarDashboard.notificationImageView.visibility = View.INVISIBLE
                        binding.appBarDashboard.profileImageView.visibility = View.INVISIBLE
                    }
                }
            }
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }
}
