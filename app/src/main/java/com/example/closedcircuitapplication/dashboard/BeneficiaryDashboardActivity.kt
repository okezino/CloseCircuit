package com.example.closedcircuitapplication.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.ActivityBeneficiaryDashboardBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class BeneficiaryDashboardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBeneficiaryDashboardBinding
    private lateinit var navController: NavController
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeneficiaryDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.appBarDashboard.contentMain.bottomNavigationView
        bottomNavigationView.background = null
        setSupportActionBar(binding.appBarDashboard.dashboardActivityToolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val drawerLayout = binding.drawerLayout
        navController = navHostFragment.navController
        bottomAppBar = binding.appBarDashboard.contentMain.bottomAppBar
        val fab = binding.appBarDashboard.contentMain.fab
        fab.setOnClickListener {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.createPlanFragment)
        }
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
//        bottomNavigationView.setupWithNavController(navController)
//        appBarConfiguration = AppBarConfiguration(
//            topLevelDestinationIds = setOf(
//                R.id.dashboardFragment
//            ),
//            drawerLayout
//        )
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
                when (destination.id) {
                    R.id.dashboardFragment -> {
                        bottomAppBar.visibility = View.VISIBLE
                        binding.appBarDashboard.contentMain.fab.visibility = View.VISIBLE
                        binding.appBarDashboard.notificationImageView.visibility = View.VISIBLE
                        binding.appBarDashboard.profileImageView.visibility = View.VISIBLE
                    }

                    else -> {
                        binding.appBarDashboard.notificationImageView.visibility = View.INVISIBLE
                        binding.appBarDashboard.profileImageView.visibility = View.INVISIBLE
                    }
                }
            }
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
    }

//    fun showAppBar() {
//        binding.appBarDashboard.contentMain.fab.visibility = View.GONE
//        bottomAppBar.visibility = View.GONE
//        binding.appBarDashboard.appBarLayout.visibility = View.VISIBLE
//        binding.appBarDashboard.notificationImageView.visibility = View.GONE
//        binding.appBarDashboard.profileImageView.visibility = View.GONE
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }
}
