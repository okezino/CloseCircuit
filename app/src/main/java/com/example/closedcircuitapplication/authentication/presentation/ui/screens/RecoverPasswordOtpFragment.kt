package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels.AuthenticationViewModel
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.showPleaseWaitAlertDialog
import com.example.closedcircuitapplication.databinding.FragmentRecoverPasswordOtpBinding
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoverPasswordOtpFragment : Fragment() {
    private var _binding: FragmentRecoverPasswordOtpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthenticationViewModel by viewModels()
    private val args: RecoverPasswordOtpFragmentArgs by navArgs()
    private var email: String = ""
    private var otp: String = ""
    private var pleaseWaitDialog: AlertDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecoverPasswordOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpannableText()


        initObservers()
        validateOtp()
        validateOtp()
        initObservers()
        pleaseWaitDialog = showPleaseWaitAlertDialog()

    }

    private fun setUpSpannableText() {
        val spannableText = SpannableString(getString(R.string.did_not_receive_email_text))
        val foregroundBlue =
            ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.spannableBlue))
        spannableText.setSpan(foregroundBlue, 22, getString(R.string.did_not_receive_email_text).length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.recoverPasswordOtpDidntReceiveEmailTextView.text = spannableText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // this function is used to validate the otp
    private fun validateOtp() {
        with(binding) {
            recoverPasswordOtpView.addTextChangedListener {
                val pin = recoverPasswordOtpView.text.toString().trim()
                if (pin.length == 6) {
                    viewModel.verifyOtp(VerifyOtpRequest(pin, args.userEmail))
                    email = args.userEmail
                    otp = pin
                } else {
                    recoverPasswordOtpView.setLineColor(resources.getColor(R.color.red))
                    recoverPasswordOtpView.setTextColor(resources.getColor(R.color.red))
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.verifyOtpResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    pleaseWaitDialog?.show()
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    pleaseWaitDialog?.dismiss()
                    val action =
                        RecoverPasswordOtpFragmentDirections.actionRecoverPasswordOtpFragmentToResetYourPasswordFragment(
                            email,
                            otp
                        )
                    findNavController().navigate(
                        action
                    )
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    //TODO(Display error message and dismiss progress bar)
                    pleaseWaitDialog?.dismiss()
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}