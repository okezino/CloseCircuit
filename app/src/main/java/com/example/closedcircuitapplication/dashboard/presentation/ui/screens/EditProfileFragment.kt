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
        binding.selectCountryCategoryDropdown.setAdapter(arrayAdapter)

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateProfileInitObserver()

        with(binding){
            fullNameTextInput.setText(args.fullName)
            phoneNumberTextInput.setText(args.phoneNumber)
            selectCountryCategoryDropdown.setText(args.nationality)
            emailTextInput.setText(args.email)
        }
//binding.fullNameTextInput.text.toString()
        //binding.emailTextInput.text.toString()
        //binding.phoneNumberTextInput.text.toString()
        binding.editProfileButton.setOnClickListener {
            viewModel.updateUserProfile(UpdateProfileRequest(binding.fullNameTextInput.text.toString(),
                binding.emailTextInput.text.toString(), binding.phoneNumberTextInput.text.toString()),
            args.userId, "${PlanConstants.BEARER} ${preferences.getToken()}")
            Log.d("request", "${binding.fullNameTextInput.text.toString()}")
            Log.d("request", "${binding.emailTextInput.text.toString()}")
            Log.d("request", "${binding.phoneNumberTextInput.text.toString()}")
            Log.d("request", "${PlanConstants.BEARER}")
            Log.d("request", "${preferences.getToken()}")
            findNavController().navigate(EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment(), customNavAnimation().build())
        }
    }

    private fun updateProfileInitObserver() {
        viewModel.updateProfileResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
//                    pleaseWaitDialog = showPleaseWaitAlertDialog()
                }
                is Resource.Success -> {
//                    pleaseWaitDialog?.dismiss()
//                    makeSnackBar(PlanConstants.PLAN_UPDATE_SUCCESS, requireView())
                }
                is Resource.Error -> {
//                    pleaseWaitDialog?.dismiss()
                    makeSnackBar("${resource.data?.message}", requireView())
                }
            }
        }
    }
}
