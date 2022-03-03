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
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels.AuthenticationViewModel
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.databinding.FragmentRecoverPasswordOtpBinding
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

    }

    private fun setUpSpannableText() {
        val text = "Didn’t recieve email? resend"
        val spannableText = SpannableString(text)
        val foregroundBlue =
            ForegroundColorSpan(requireActivity().resources.getColor(R.color.spannableBlue))
        spannableText.setSpan(foregroundBlue, 22, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.recoverPasswordOtpDidntReceiveEmailTextView.text = spannableText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // this function is used to validate the otp
    private fun validateOtp() {
        binding.recoverPasswordOtpView.addTextChangedListener {
            val pin = binding.recoverPasswordOtpView.text.toString().trim()
            if (pin.length == 6) {
                viewModel.verifyOtp(VerifyOtpRequest(pin, args.userEmail))
                email = args.userEmail
                otp = pin

            } else {
                binding.recoverPasswordOtpView.setLineColor(resources.getColor(R.color.red))
                binding.recoverPasswordOtpView.setTextColor(resources.getColor(R.color.red))
            }

        }
    }

    private fun initObservers(){
        viewModel.verifyOtpResponse.observe(viewLifecycleOwner, { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showPleaseWaitAlertDialog()
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    showPleaseWaitAlertDialog().dismiss()
                    val action = RecoverPasswordOtpFragmentDirections.actionRecoverPasswordOtpFragmentToResetYourPasswordFragment(email, otp)
                    findNavController().navigate(
                        action
                    )
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
                    //TODO(Display error message and dismiss progress bar)
                    showPleaseWaitAlertDialog().dismiss()
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT)
                        .show()

                }
            }


        })

    }

    private fun showPleaseWaitAlertDialog(): AlertDialog {
        if(pleaseWaitDialog == null) {
            pleaseWaitDialog = showCustomViewDialog(
                requireContext(), resources,
                R.layout.custom_login_wait_dialog,
                false
            )
        }
        return pleaseWaitDialog as AlertDialog
    }
}