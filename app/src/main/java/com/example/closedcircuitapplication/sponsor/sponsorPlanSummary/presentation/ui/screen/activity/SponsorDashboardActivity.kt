package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.ui.screen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.ActivitySponsorDashboardBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import android.view.Gravity
import android.view.View
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.closedcircuitapplication.common.common.utils.showLogOutDialog
import com.example.closedcircuitapplication.common.common.utils.showToast
import com.example.closedcircuitapplication.common.common.utils.userLogOut
import com.example.closedcircuitapplication.databinding.DrawerHeaderLayoutBinding
import com.example.closedcircuitapplication.databinding.LogoutDialogLayoutBinding

@AndroidEntryPoint
class SponsorDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySponsorDashboardBinding
    private lateinit var navController: NavController
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var drawerHeaderBinding : DrawerHeaderLayoutBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySponsorDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.sponsorAppBarDashboard.spDashboardActivityToolbar)
        bottomAppBar = binding.sponsorAppBarDashboard.sponsorBottomAppBar
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.sponsors_nav_host_fragment_container) as NavHostFragment
        navController= navHostFragment.navController

        setUpBottomNavigation()
        setUpDrawableLayout()
        activateClickListener()
        setUpNavigationDestinations()
        onDestinationChangedListener()
    }

    private  fun setUpBottomNavigation(){
        val bottomNavigationView = binding.sponsorAppBarDashboard.sponsorBottomNavigationView
            bottomNavigationView.background = null
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    private fun setUpDrawableLayout(){
        drawerHeaderBinding = DrawerHeaderLayoutBinding.bind(binding.spDrawerNavView.getHeaderView(0))
        val drawableLayout = binding.sponsorDrawerLayout
        appBarConfiguration = AppBarConfiguration(navController.graph, drawableLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
    private  fun activateClickListener(){
        drawerHeaderBinding.drawerHeaderCloseIconImageView.setOnClickListener {
            binding.sponsorDrawerLayout.close()
        }
        binding.sponsorAppBarDashboard.sponsorFab.setOnClickListener {
            findNavController(R.id.sponsors_nav_host_fragment_container).navigate(R.id.sponsorsDashboardFragment)
        }

        binding.sponsorAppBarDashboard.spNotificationImageView.setOnClickListener {
            showToast(this, "notification Screen fragment")
        }

        binding.spDrawerLogoutConstraintLayout.setOnClickListener {
            logOut()
        }
    }

    private fun logOut(){
        val logoutDialogLayoutBinding = LogoutDialogLayoutBinding.inflate(layoutInflater)
        val logoutDialog = showLogOutDialog(this, logoutDialogLayoutBinding,resources, {userLogOut()})
        logoutDialog.show()
    }
    private fun setUpNavigationDestinations() {
        binding.spDrawerNavView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.notificationScreenFragment -> {
                    //ToDo( navigate to the notificationScreenFragment)
                    showToast(this, "notification Screen fragment")
                    binding.sponsorDrawerLayout.closeDrawer(Gravity.LEFT)
                    return@setNavigationItemSelectedListener true
                }
                R.id.supportFragment ->{
                    //ToDo( navigate to the supportFragment)
                    showToast(this, "support screen fragment")
                    binding.sponsorDrawerLayout.closeDrawer(Gravity.LEFT)
                    return@setNavigationItemSelectedListener true
                }
                R.id.drawer_about_us ->{
                    //ToDo( navigate to the about_us Fragment)
                    showToast(this, "about us fragment")
                    binding.sponsorDrawerLayout.closeDrawer(Gravity.LEFT)
                    return@setNavigationItemSelectedListener true
                }
                R.id.drawer_settings -> {
                    //ToDo( navigate to the settings Fragment)
                    showToast(this, "setting fragment")
                    binding.sponsorDrawerLayout.closeDrawer(Gravity.LEFT)
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun onDestinationChangedListener() {
        try {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomAppBar.visibility = View.VISIBLE
                binding.sponsorAppBarDashboard.sponsorFab.visibility = View.VISIBLE
                binding.sponsorAppBarDashboard.spAppBarLayout.visibility = View.VISIBLE
                when (destination.id) {
                    R.id.dashboardFragment -> {
                        bottomAppBar.visibility = View.VISIBLE
                        binding.sponsorAppBarDashboard.sponsorFab.visibility = View.VISIBLE
                        binding.sponsorAppBarDashboard.spNotificationImageView.visibility = View.VISIBLE
                        binding.sponsorAppBarDashboard.spProfileImageView.visibility = View.VISIBLE
                        binding.sponsorAppBarDashboard.spToolbarHeaderTitleTv.visibility= View.INVISIBLE
                    }
                }
            }
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.sponsors_nav_host_fragment_container)
        return navController.navigateUp(appBarConfiguration)||
        super.onSupportNavigateUp()
    }

}