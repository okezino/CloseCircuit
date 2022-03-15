package com.example.closedcircuitapplication.dashboard.presentation.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.utils.*
import com.example.closedcircuitapplication.dashboard.domain.model.UpdateProfileRequest
import com.example.closedcircuitapplication.dashboard.presentation.ui.viewmodel.DashboardViewModel
import com.example.closedcircuitapplication.databinding.FragmentEditProfileBinding
import com.example.closedcircuitapplication.plan.presentation.ui.screens.EditPlanFragmentDirections
import com.example.closedcircuitapplication.plan.utils.PlanConstants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private  var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val args: EditProfileFragmentArgs by navArgs()
    lateinit var email: String
    lateinit var fullName: String
    lateinit var phoneNumber: String
    lateinit var countryCode: String

    private val viewModel: DashboardViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val nationality = resources.getStringArray(R.array.Choose_country)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, nationality)
//        binding.selectCountryCategoryDropdown.setAdapter(arrayAdapter)

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateProfileInitObserver()

        with(binding){
            editProfileFullNameTextInput.setText(args.fullName)
            val phone = PhoneNumberSplitter.phoneNumberCode(args.phoneNumber)
            editProfilePhoneNumberTextInput.setText(phone)
//            selectCountryCategoryDropdown.setText(args.nationality)
            editProfileEmailTextInput.setText(args.email)
        }

        binding.editProfileButton.setOnClickListener {
            fullName = binding.editProfileFullNameTextInput.text.toString()
            email = binding.editProfileEmailTextInput.text.toString()
            phoneNumber = binding.editProfilePhoneNumberTextInput.text.toString()
            countryCode = binding.fragmentEditProfileCountryCodePicker.selectedCountryCodeWithPlus

            if (fullName.isBlank()) {
                binding.editProfileFullNameTextInput.error = "Full name can't be empty"
            } else if (!Validation.validateEmailInput(email)) {
                binding.editProfileEmailTextInput.error = "cant be empty"
            } else if (Validation.validatePhone_number(phoneNumber)) {
                binding.editProfilePhoneNumberTextInput.error = "Incomplete number"
            } else {

                viewModel.updateUserProfile(
                    UpdateProfileRequest(
                        binding.editProfileFullNameTextInput.text.toString(),
                        binding.editProfileEmailTextInput.text.toString(),
                        countryCode+phoneNumber),
                    args.userId, "${PlanConstants.BEARER} ${preferences.getToken()}"
                )

                findNavController().navigate(
                    EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment(),
                    customNavAnimation().build()
                )
            }

        }
    }

    private fun updateProfileInitObserver() {
        viewModel.updateProfileResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                }
                is Resource.Error -> {
                    makeSnackBar("${resource.data?.message}", requireView())
                }
            }
        }
    }
}
