package com.example.closedcircuitapplication.common.common.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.utils.FROM_LOGOUT
import com.example.closedcircuitapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var bottomAppBar: BottomAppBar
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val logoutExtras = intent.getBooleanExtra(FROM_LOGOUT, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()

//        if (logoutExtras) navController.navigate(R.id.loginFragment,null, navOptions)


    }

}
