package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.dashboard.presentation.ui.viewmodel.DashboardViewModel
import com.example.closedcircuitapplication.databinding.FragmentEmailVerificationBinding
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.plan.utils.PlanUtils
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.PlanViewModel
import com.example.closedcircuitapplication.plan.utils.PlanConstants
import com.example.closedcircuitapplication.plan.utils.PlanConstants.LOADING
import com.example.closedcircuitapplication.plan.utils.PlanConstants.OTP_MESSAGE
import com.example.closedcircuitapplication.plan.utils.PlanConstants.VERIFICATION_MESSAGE
import com.example.closedcircuitapplication.plan.utils.PlanConstants.text
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmailVerificationFragment : Fragment(R.layout.fragment_email_verification) {
    private var _binding: FragmentEmailVerificationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlanViewModel by viewModels()
    private  val viewModelDashboard: DashboardViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentEmailVerificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displayEmail = PlanUtils.userEmailDisplayText(preferences.getEmail())
        (PlanConstants.DISPLAY_TEXT_START+displayEmail+PlanConstants.DISPLAY_TEXT_END).also { binding.verifyEmailNotificationMessage.text = it }

        setUpSpannableText()
        validateOtp()
        initObservers()
        initObserversResendOtp()
        userDetailsInitObserver()
        getUserDetails()

        binding.closeIcon.setOnClickListener {
            findNavController().navigate(EmailVerificationFragmentDirections.actionEmailVerificationFragmentToProjectScreenFragment())
        }
        binding.recoverPasswordOtpDidntReceiveEmailTextView.setOnClickListener {
            viewModel.generateOtp(GenerateOtpRequest(preferences.getEmail()))
        }

        binding.recoverPasswordOtpDidntReceiveEmailTextView.setOnClickListener {
            viewModel.generateOtp(GenerateOtpRequest(preferences.getEmail()))
        }

    }
    private fun validateOtp() {
        binding.fragmentEmailVerificationRecoverEmailOtpView.addTextChangedListener {
            val pin = binding.fragmentEmailVerificationRecoverEmailOtpView.text.toString().trim()
            if (pin.length == 6) {
                viewModel.verifyOtp(VerifyOtpRequest(pin,preferences.getEmail()))
            } else {
                with(binding){
                    fragmentEmailVerificationRecoverEmailOtpView.setLineColor(ContextCompat.getColor(requireContext(),R.color.red))
                    fragmentEmailVerificationRecoverEmailOtpView.setTextColor(ContextCompat.getColor(requireContext(),R.color.red))
                }
            }

        }
    }

    private fun initObservers(){
        viewModel.verifyOtpResponse.observe(viewLifecycleOwner, { resource ->
            when (resource) {
                is Resource.Loading -> {
                    makeSnackBar(LOADING,requireView())
                }
                is Resource.Success -> {
                    findNavController().navigate(EmailVerificationFragmentDirections.actionEmailVerificationFragmentToSuccessfulEmailVerificationScreenFragment())
                    makeSnackBar(VERIFICATION_MESSAGE,requireView())
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
                    makeSnackBar(LOADING, requireView())
                }
                is Resource.Success -> {
                    makeSnackBar("$OTP_MESSAGE ${preferences.getEmail()}", requireView())
                }
                is Resource.Error -> {
                    resource.data?.message?.let { makeSnackBar(it,requireView()) }
                }
            }
        }
    }

    private fun userDetailsInitObserver(){
        viewModelDashboard.userDetailsResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->{
                }
                is Resource.Success ->{
                    it.data.data?.isVerified?.let { status -> saveEmailStatus(status) }
                }
                is Resource.Error ->{
                    makeSnackBar("${it.data?.message}", requireView())
                }
            }
        }
    }

    private fun getUserDetails(){
        viewModelDashboard.getUserDetails(preferences.getUserId(), "Bearer ${preferences.getToken()}")
    }

    private fun setUpSpannableText() {
        val spannableText = SpannableString(text)
        val foregroundBlue = ForegroundColorSpan(ContextCompat.getColor(requireActivity(),R.color.spannableBlue))
        spannableText.setSpan(foregroundBlue, 22, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.recoverPasswordOtpDidntReceiveEmailTextView.text = spannableText
    }
    private fun saveEmailStatus(status: Boolean) = preferences.putUserVerified(status, PreferencesConstants.USER_VERIFIED)
}
