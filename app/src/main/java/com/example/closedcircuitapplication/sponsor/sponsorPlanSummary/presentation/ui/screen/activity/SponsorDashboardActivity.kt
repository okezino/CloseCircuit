package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.ui.screen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.closedcircuitapplication.databinding.ActivitySponsorDashboardBinding

class SponsorDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySponsorDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySponsorDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sponsorBottomNavigationView.background = null
    }

}