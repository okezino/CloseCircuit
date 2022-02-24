package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.databinding.FragmentEmailVerificationBinding
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.plan.utils.PlanUtils
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.PlanViewModel
import com.example.closedcircuitapplication.plan.utils.PlanConstants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmailVerificationFragment : Fragment(R.layout.fragment_email_verification) {
    private var _binding: FragmentEmailVerificationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlanViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences
    private var userEmail: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmailVerificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefEmail = preferences.getEmail()
        userEmail = prefEmail

        val displayEmail = PlanUtils.userEmailDisplayText(userEmail)
        (PlanConstants.DISPLAY_TEXT_START+displayEmail+PlanConstants.DISPLAY_TEXT_END).also { binding.verifyEmailNotificationMessage.text = it }

        validateOtp()
        initObservers()
        initObserversResendOtp()

        binding.closeIcon.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.recoverPasswordOtpDidntReceiveEmailTextView.setOnClickListener {
            val email: String = prefEmail
            viewModel.generateOtp(GenerateOtpRequest(email))
        }

    }
    private fun validateOtp() {
        binding.fragmentEmailVerificationRecoverEmailOtpView.addTextChangedListener {
            val pin = binding.fragmentEmailVerificationRecoverEmailOtpView.text.toString().trim()
            if (pin.length == 6) {
                viewModel.verifyOtp(VerifyOtpRequest(pin,userEmail))
            } else {
                binding.fragmentEmailVerificationRecoverEmailOtpView.setLineColor(resources.getColor(R.color.red))
                binding.fragmentEmailVerificationRecoverEmailOtpView.setTextColor(resources.getColor(R.color.red))
            }

        }
    }

    private fun initObservers(){
        viewModel.verifyOtpResponse.observe(viewLifecycleOwner, { resource ->
            when (resource) {
                is Resource.Loading -> {
                    makeSnackBar("Loading",requireView())
                }
                is Resource.Success -> {
                    findNavController().navigate(EmailVerificationFragmentDirections.actionEmailVerificationFragmentToSuccessfulEmailVerificationScreenFragment())
                    makeSnackBar("Email verification successful",requireView())
                    preferences.putUserVerified(true, PreferencesConstants.USER_VERIFIED)
                }
                is Resource.Error -> {
                    makeSnackBar("${resource.data?.message}",requireView())
                }
            }
        })
    }
    private fun initObserversResendOtp(){
        viewModel.generateOtpResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Otp sent to $userEmail", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
