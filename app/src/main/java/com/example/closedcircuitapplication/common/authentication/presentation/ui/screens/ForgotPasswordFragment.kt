package com.example.closedcircuitapplication.common.authentication.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.authentication.presentation.ui.viewmodels.AuthenticationViewModel
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.common.common.utils.Validation
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.common.utils.showPleaseWaitAlertDialog
import com.example.closedcircuitapplication.databinding.FragmentForgotPasswordBinding
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.GenerateOtpRequest
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    lateinit var email: String
    private val viewModel: AuthenticationViewModel by viewModels()
    private var userEmail: String = ""
    private var pleaseWaitDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpannableText()
        initObservers()
        pleaseWaitDialog = showPleaseWaitAlertDialog()

        binding.forgotPasswordEmailTv.addTextChangedListener(buttonHandler)

        binding.forgotPasswordButton.setOnClickListener {
            val email = binding.forgotPasswordEmailTv.text.toString().trim()

            if (Validation.validateEmailPattern(email)) {
                viewModel.generateOtp(GenerateOtpRequest(email))
                userEmail = email
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.invalid_email_address_text),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private val buttonHandler: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val email = binding.forgotPasswordEmailTv.text.toString().trim()
            binding.forgotPasswordButton.isEnabled =
                email.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}

    }

    private fun setUpSpannableText() {
        val text = getString(R.string.remember_password_text)
        val spannableText = SpannableString(text)
        val foregroundBlue =
            ForegroundColorSpan(requireActivity().resources.getColor(R.color.spannableBlue))
        spannableText.setSpan(foregroundBlue, 19, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.forgotPasswordRememberPasswordTextView.text = spannableText
    }

    private fun initObservers() {
        viewModel.generateOtpRequest.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showPleaseWaitAlertDialog().dismiss()
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    pleaseWaitDialog?.show()
                }
                is Resource.Success -> {
                    pleaseWaitDialog?.dismiss()
                    findNavController().navigate(
                        ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToRecoverPasswordOtpFragment(
                            userEmail
                        ), customNavAnimation().build()
                    )
                    Toast.makeText(requireContext(), getText(R.string.success_text), Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    pleaseWaitDialog?.dismiss()
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}