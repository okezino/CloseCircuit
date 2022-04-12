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

@AndroidEntryPoint
class SponsorDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySponsorDashboardBinding
    private lateinit var navController: NavController
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySponsorDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sponsorBottomNavigationView.background = null

        bottomNavigationView = binding.sponsorBottomNavigationView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

}