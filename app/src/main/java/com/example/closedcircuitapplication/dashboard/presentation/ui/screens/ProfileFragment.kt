package com.example.closedcircuitapplication.dashboard.presentation.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.presentation.ui.screens.CreateAccountFragmentDirections
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.UserNameSplitterUtils
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.dashboard.presentation.ui.viewmodel.DashboardViewModel
import com.example.closedcircuitapplication.databinding.FragmentProfileBinding
import com.example.closedcircuitapplication.plan.presentation.ui.screens.ProjectScreenFragmentDirections
import com.example.closedcircuitapplication.plan.presentation.ui.screens.ProjectsAdapter
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.PlanViewModel
import com.example.closedcircuitapplication.plan.utils.onItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private  var _binding:FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private  val viewModel: DashboardViewModel by viewModels()
    private val _viewModel: PlanViewModel by viewModels()

    private lateinit var fullName: String
    private lateinit var phoneNumber: String
    private lateinit var nationality: String
    private lateinit var userId: String
    private lateinit var email: String

    @Inject
    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserDetails()
        userDetailsInitObserver()
        initObserversMyPlansTotal()

        _viewModel.getMyPlans(100, 0, "Bearer ${preferences.getToken()}")
       }

    private fun userDetailsInitObserver(){
        viewModel.userDetailsResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->{
                }
                is Resource.Success ->{
                    fullName = it.data.data?.fullName.toString()
                    userId = it.data.data?.id.toString()
                    val firstName = UserNameSplitterUtils.userFullName(fullName)
                    binding.profileName.text = fullName
                    binding.profileEmail.text = it.data.data?.email
                    phoneNumber = it.data.data?.phoneNumber.toString()
                    nationality = it.data.data?.country.toString()
                    email = it.data.data?.email.toString()
                    binding.profileNumber.text = it.data.data?.phoneNumber
                    binding.profileNationality.text = it.data.data?.country

                    binding.profileEditButton.setOnClickListener {

                        findNavController().navigate(
                            ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(fullName,phoneNumber, nationality, userId, email),
                            customNavAnimation().build())

                    }

                    // saving email to sharedPreference
//                    it.data.data?.let { email -> saveEmail(email.email) }
                    //save verification status to sharedPreference
                    it.data.data?.isVerified?.let { status -> saveEmailStatus(status) }
                    // save user first name to sharedPreference
//                    it.data.data?.fullName?.let { name -> saveUserFirstName(name) }
                    // save user phone number to sharedPreference
//                    it.data.data?.phoneNumber?.let { number -> saveUserPhoneNumber(number)}
                    Log.d("NUMBER", "PHONE_NUMBER ${it.data.data?.phoneNumber}")
                }
                is Resource.Error ->{
                    makeSnackBar("${it.data?.message}", requireView())
                }
            }
        }
    }

    private fun initObserversMyPlansTotal(){
        _viewModel.getMyPlansResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    makeSnackBar("Loading...", requireView())
                }
                is Resource.Success -> {
                    resource.data.data?.plans
                    binding.plansTotal.text =  resource.data.data?.plans?.size.toString()
                }
                is Resource.Error -> {
                    makeSnackBar("${resource.data?.message}",requireView())
                }
            }
        }
    }

    private fun getUserDetails(){
        viewModel.getUserDetails(preferences.getUserId(), "Bearer ${preferences.getToken()}")
    }

    private fun saveEmail(email: String) = preferences.saveEmail(email)
    private fun saveEmailStatus(status: Boolean) = preferences.putUserVerified(status, PreferencesConstants.USER_VERIFIED)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}