package com.example.closedcircuitapplication.beneficiary.dashboard.presentation.view.screens

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.common.data.preferences.PreferencesConstants
import com.example.closedcircuitapplication.common.common.data.preferences.PreferencesConstants.USER_FULL_NAME
import com.example.closedcircuitapplication.common.common.data.preferences.PreferencesConstants.USER_PHONE_NUMBER
import com.example.closedcircuitapplication.common.common.utils.*
import com.example.closedcircuitapplication.beneficiary.dashboard.presentation.view.viewmodel.DashboardViewModel
import com.example.closedcircuitapplication.databinding.ActivityBeneficiaryDashboardBinding
import com.example.closedcircuitapplication.databinding.DrawerHeaderLayoutBinding
import com.example.closedcircuitapplication.databinding.LogoutDialogLayoutBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BeneficiaryDashboardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityBeneficiaryDashboardBinding
    private lateinit var navController: NavController
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var drawerHeaderLayoutBinding: DrawerHeaderLayoutBinding
    private lateinit var logoutDialogLayoutBinding: LogoutDialogLayoutBinding
    private  val viewModel: DashboardViewModel by viewModels()
    private lateinit var  logoutDialog: AlertDialog

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeneficiaryDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getUserDetails(preferences.getUserId(PreferencesConstants.USER_ID),"Bearer ${preferences.getToken()}")

        bottomNavigationView = binding.appBarDashboard.contentMain.bottomNavigationView
        bottomNavigationView.background = null
        setSupportActionBar(binding.appBarDashboard.dashboardActivityToolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val drawerLayout = binding.drawerLayout
        navController = navHostFragment.navController

        bottomAppBar = binding.appBarDashboard.contentMain.bottomAppBar
        val fab = binding.appBarDashboard.contentMain.fab
        fab.setOnClickListener {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.projectScreenFragment)
        }
        val notificationIcon = binding.appBarDashboard.notificationImageView
        notificationIcon.setOnClickListener {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.notificationScreenFragment)
        }
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        drawerHeaderLayoutBinding = DrawerHeaderLayoutBinding.bind(binding.drawerNavView.getHeaderView(0))

        logoutDialogLayoutBinding = LogoutDialogLayoutBinding.inflate(layoutInflater)
        logoutDialog = showLogOutDialog(this, logoutDialogLayoutBinding,resources, {userLogOut()})

        drawerHeaderLayoutBinding.drawerHeaderCloseIconImageView.setOnClickListener {
            binding.drawerLayout.close()
        }
        binding.drawerLogoutConstraintLayout.setOnClickListener {
            logoutDialog.show()
        }

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        onDestinationChangedListener()
        userDetailsInitObserver()
        setUpNavigationDestinations()
    }


    private fun setUpNavigationDestinations() {
        binding.drawerNavView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.notificationScreenFragment -> {
                    findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.notificationScreenFragment)
                    binding.drawerLayout.closeDrawer(Gravity.LEFT)
                    return@setNavigationItemSelectedListener true
                }
                R.id.supportFragment ->{
                    findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.supportFragment)
                    binding.drawerLayout.closeDrawer(Gravity.LEFT)
                    return@setNavigationItemSelectedListener true
                }
                R.id.drawer_about_us ->{
                    findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.LearnAboutUsFragment)
                    binding.drawerLayout.closeDrawer(Gravity.LEFT)
                    return@setNavigationItemSelectedListener true
                }
                R.id.drawer_settings -> {
                    findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.settingsFragment)
                    binding.drawerLayout.closeDrawer(Gravity.LEFT)
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
                binding.appBarDashboard.contentMain.fab.visibility = View.VISIBLE
                binding.appBarDashboard.appBarLayout.visibility = View.VISIBLE
                when (destination.id) {
                    R.id.dashboardFragment -> {
                        bottomAppBar.visibility = View.VISIBLE
                        binding.appBarDashboard.contentMain.fab.visibility = View.VISIBLE
                        binding.appBarDashboard.notificationImageView.visibility = View.VISIBLE
                        binding.appBarDashboard.profileImageView.visibility = View.VISIBLE
                        binding.appBarDashboard.toolbarHeaderTitleTv.visibility=View.INVISIBLE
                    }
                    R.id.supportFragment->{
                      //  binding.appBarDashboard.notificationImageView.visibility = View.INVISIBLE
                       // binding.appBarDashboard.profileImageView.visibility = View.INVISIBLE
                      //  binding.appBarDashboard.toolbarHeaderTitleTv.visibility=View.VISIBLE
                    }
                    else -> {
                      //  binding.appBarDashboard.notificationImageView.visibility = View.INVISIBLE
                      //  binding.appBarDashboard.profileImageView.visibility = View.INVISIBLE
                     //   binding.appBarDashboard.toolbarHeaderTitleTv.visibility=View.INVISIBLE
                    }
                }
            }
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
    }

    private fun userDetailsInitObserver(){
        viewModel.userDetailsResponse.observe(this){
            when(it){
                is Resource.Loading ->{
                }
                is Resource.Success ->{
                    val fullName = it.data.data?.fullName.toString()
                    val firstName = UserNameSplitterUtils.userFullName(fullName)
                    it.data.data?.fullName.let { fullName ->
                        if (fullName != null) {
                            preferences.putUserFullName(
                                USER_FULL_NAME, fullName)
                        }
                    }
                    drawerHeaderLayoutBinding.drawerHeaderUserFullNameTextView.text = fullName
                    // saving email to sharedPreference
                    it.data.data?.let { email -> preferences.saveEmail(email.email) }
                    //save verification status to sharedPreference
                    it.data.data?.isVerified?.let { status -> preferences.putUserVerified(status, PreferencesConstants.USER_VERIFIED) }
                    // save user first name to sharedPreference
                    firstName.let { name -> preferences.putUserFirstName(name) }
                    // save user phone number to sharedPreference
                    it.data.data?.phoneNumber?.let { number -> preferences.putUserPhoneNumber(USER_PHONE_NUMBER, number)}
                }
                is Resource.Error ->{
                }
            }
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }
}
