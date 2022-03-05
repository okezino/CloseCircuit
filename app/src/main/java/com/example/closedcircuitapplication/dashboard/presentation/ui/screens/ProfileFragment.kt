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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private  var _binding:FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private  val viewModel: DashboardViewModel by viewModels()

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


        binding.profileEditButton.setOnClickListener {

            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(),
                customNavAnimation().build())

        }

        getUserDetails()
        userDetailsInitObserver()


       }

    private fun userDetailsInitObserver(){
        viewModel.userDetailsResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->{
                }
                is Resource.Success ->{
                    val fullName = it.data.data?.fullName.toString()
                    val firstName = UserNameSplitterUtils.userFullName(fullName)
                    binding.profileName.text = fullName
                    binding.profileEmail.text = it.data.data?.email
                    binding.profileNumber.text = it.data.data?.phoneNumber
                    binding.profileNationality.text = it.data.data?.country
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