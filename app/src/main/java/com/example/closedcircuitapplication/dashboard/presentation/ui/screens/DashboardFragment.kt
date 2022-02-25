package com.example.closedcircuitapplication.dashboard.presentation.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.USER_ID
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.USER_PHONE_NUMBER
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.dashboard.presentation.ui.adapter.PlansAdapter
import com.example.closedcircuitapplication.dashboard.presentation.ui.adapter.RecentDonationsAdapter
import com.example.closedcircuitapplication.dashboard.presentation.models.DonationItem
import com.example.closedcircuitapplication.dashboard.presentation.models.PlanItems
import com.example.closedcircuitapplication.common.utils.UserNameSplitterUtils
import com.example.closedcircuitapplication.dashboard.presentation.ui.viewmodel.DashboardViewModel
import com.example.closedcircuitapplication.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var plansAdapter: PlansAdapter
    private lateinit var plansRecyclerView: RecyclerView
    private lateinit var recentDonationsAdapter: RecentDonationsAdapter
    private lateinit var recentDonationsRecyclerView: RecyclerView
    private  val viewModel: DashboardViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        plansRecyclerView = binding.activeUserConstraint.plansConstraint
        recentDonationsRecyclerView = binding.activeUserConstraint.recentDonationsRecyclerView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserDetails(preferences.getUserId(USER_ID),"Bearer ${preferences.getToken()}")


        userDetailsInitObserver()
        getUserDetails()
        /*TODO: Implement Create plan. For now this button displays an active user UI*/
        binding.createPlanButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections
                .actionDashboardFragmentToProjectScreenFragment2(),
                customNavAnimation().build())
            binding.newUserConstraint.visibility = View.GONE
            if (!binding.activeUserConstraint.activeUserConstraint.isVisible) {
                binding.activeUserConstraint.activeUserConstraint.visibility = View.VISIBLE
            }
        }
        createPlans()
        getRecentDonations()
    }

    private fun createPlans() {
        val plans = ArrayList<PlanItems>()
        plans.add(PlanItems("School Fees", "20% Funds Raised", "10% Tasks Completed", R.drawable.fish_farming))
        plans.add(PlanItems("Fish Farming", "70% Funds Raised", "40% Tasks Completed", R.drawable.diane_russell))
        plans.add(PlanItems("Book Launch", "52% Funds Raised", "5% Tasks Completed", R.drawable.fish_farming))
        plans.add(PlanItems("Detty December", "20% Funds Raised", "15% Tasks Completed", R.drawable.diane_russell))
        plans.add(PlanItems("Amazon Trip", "5% Funds Raised", "0% Tasks Completed", R.drawable.fish_farming))

        plansAdapter = PlansAdapter(plans)

        plansRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        plansRecyclerView.adapter = plansAdapter
    }

    private fun getRecentDonations() {
        val donations = ArrayList<DonationItem>()
        donations.add(DonationItem("Dianne Russell", "NGN 40,000.00", "Fish Farming", R.drawable.diane_russell))
        donations.add(DonationItem("Ayo Makun", "NGN 8,000.00", "Detty December", R.drawable.diane_russell))
        donations.add(DonationItem("Sarah Knowles", "NGN 5,000.00", "Fish Farming", R.drawable.diane_russell))
        donations.add(DonationItem("Jenny Wilson", "NGN 10,000.00", "Fish Farming", R.drawable.diane_russell))
        donations.add(DonationItem("Ronald Richard", "NGN 20,000.00", "School Fees", R.drawable.diane_russell))

        recentDonationsAdapter = RecentDonationsAdapter(donations)

        recentDonationsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recentDonationsRecyclerView.adapter = recentDonationsAdapter
    }

    private fun userDetailsInitObserver(){
        viewModel.userDetailsResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->{
                }
                is Resource.Success ->{
                    val fullName = it.data.data?.fullName.toString()
                    val firstName = UserNameSplitterUtils.userFullName(fullName)
                    binding.welcomeTitle.text = getString(R.string.welcome_patience, firstName)
                    // saving email to sharedPreference
                    it.data.data?.let { email -> saveEmail(email.email) }
                    //save verification status to sharedPreference
                    it.data.data?.isVerified?.let { status -> saveEmailStatus(status) }
                    // save user first name to sharedPreference
                    it.data.data?.fullName?.let { name -> saveUserFirstName(name) }
                    // save user phone number to sharedPreference
                    it.data.data?.phoneNumber?.let { number -> saveUserPhoneNumber(number)}
                    Log.d("NUMBER", "PHONE_NUMBER ${it.data.data?.phoneNumber}")
                }
                is Resource.Error ->{
                    makeSnackBar("${it.data?.message}", requireView())
                }
            }
        }
    }

    private fun getUserDetails(){
        viewModel.getUserDetails(preferences.getUserId(), "Bearer ${preferences.getToken()}")
    }
    private fun saveEmail(email: String) = preferences.saveEmail(email)
    private fun saveEmailStatus(status: Boolean) = preferences.putUserVerified(status, PreferencesConstants.USER_VERIFIED)
    private fun saveUserPhoneNumber(phoneNumber: String) = preferences.putUserPhoneNumber(
        USER_PHONE_NUMBER,phoneNumber)
    private fun saveUserFirstName(firstName: String) = preferences.putUserFirstName(firstName)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
