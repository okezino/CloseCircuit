package com.example.closedcircuitapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
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
       // binding.

       // binding.appBarDashboard.contentMain.bottomNavigationView.background = null
        setSupportActionBar(binding.dashboardActivityToolbar)

        val drawerLayout = binding.drawerLayout
        navController = findNavController(R.id.nav_host_fragment_content_main)
       // bottomAppBar = binding.appBarDashboard.contentMain.bottomAppBar
       // bottomNavigationView = binding.appBarLayout.contentMain.bottomNavigationView
        bottomNavigationView = binding.contentMain.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
       // appBarConfiguration = AppBarConfiguration(topLevelDestinationIds = setOf(
//            R.id.dashboardFragment2
//        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        onDestinationChangedListener()
    }

    private fun onDestinationChangedListener() {
        try {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomAppBar.visibility = View.VISIBLE
                binding.contentMain.fab.visibility = View.VISIBLE
                binding.appBarLayout.visibility = View.GONE
                binding.appBarLayout.visibility = View.VISIBLE
                when (destination.id) {
                    R.id.dashboardFragment -> {
                        bottomAppBar.visibility = View.VISIBLE
                        binding.contentMain.fab.visibility = View.VISIBLE
                        binding.notificationImageView.visibility = View.VISIBLE
                        binding.profileImageView.visibility = View.VISIBLE
                        binding.appBarLayout.visibility = View.VISIBLE
                    }
//                    R.id.dashboardFragment2 -> {
//                        bottomAppBar.visibility = View.VISIBLE
//                        binding.appBarDashboard.contentMain.fab.visibility = View.VISIBLE
//                        binding.appBarDashboard.notificationImageView.visibility = View.VISIBLE
//                        binding.appBarDashboard.profileImageView.visibility = View.VISIBLE
//                    }
                    R.id.createAPlanFragment ->{
                        bottomAppBar.visibility = View.VISIBLE
                        binding.appBarDashboard.contentMain.fab.visibility = View.VISIBLE
                        binding.appBarDashboard.notificationImageView.visibility = View.GONE
                        binding.appBarDashboard.profileImageView.visibility = View.GONE
                    }

                    R.id.loginFragment ->  showAppBar()
                    R.id.createAccountFragment ->  showAppBar()
                    R.id.recoverPasswordOtpFragment -> showAppBar()
                    R.id.resetYourPasswordFragment -> showAppBar()
                    R.id.forgotPasswordFragment ->  showAppBar()

                    else -> {
                        binding.appBarDashboard.contentMain.fab.visibility = View.GONE
                        bottomAppBar.visibility = View.GONE
                        binding.appBarDashboard.appBarLayout.visibility = View.GONE
                        binding.appBarDashboard.notificationImageView.visibility = View.GONE
                        binding.appBarDashboard.profileImageView.visibility = View.GONE
                    }
                }
            }
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
    }

    fun showAppBar(){
        binding.appBarDashboard.contentMain.fab.visibility = View.GONE
        bottomAppBar.visibility = View.GONE
        binding.appBarDashboard.appBarLayout.visibility = View.VISIBLE
        binding.appBarDashboard.notificationImageView.visibility = View.GONE
        binding.appBarDashboard.profileImageView.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }
}
