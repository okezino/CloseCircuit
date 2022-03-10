package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.domain.models.ResetPasswordRequest
import com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels.AuthenticationViewModel
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Validation.validateResetPassword
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.showPleaseWaitAlertDialog
import com.example.closedcircuitapplication.databinding.FragmentResetYourPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetYourPasswordFragment : Fragment() {
    private var pleaseWaitDialog: AlertDialog? = null
    private var _binding: FragmentResetYourPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthenticationViewModel by viewModels()
    private val args: ResetYourPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResetYourPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pleaseWaitDialog = showPleaseWaitAlertDialog()
        binding.newPasswordTv.addTextChangedListener(buttonHandler)
        binding.confirmPasswordTv.addTextChangedListener(buttonHandler)
        resetPassword()
        initObservers()

    }

    private val buttonHandler: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val newPassword = binding.newPasswordTv.text.toString().trim()
            val confirmPassword = binding.confirmPasswordTv.text.toString().trim()
            binding.resetBtn.isEnabled =
                newPassword.isNotEmpty() && confirmPassword.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}

    }

    private fun resetPassword() {
        binding.resetBtn.setOnClickListener {
            val newPassword = binding.newPasswordTv.text.toString().trim()
            val confirmPassword = binding.confirmPasswordTv.text.toString().trim()
            if (validateResetPassword(newPassword, confirmPassword)) {
                viewModel.resetPassword(
                    ResetPasswordRequest(
                        args.userEmail, args.otp, newPassword, confirmPassword
                    )
                )
            }
        }
    }

    private fun initObservers() {
        viewModel.resetPasswordResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    pleaseWaitDialog?.show()
                    Toast.makeText(requireContext(), getString(R.string.loading_text), Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    pleaseWaitDialog?.dismiss()
                    findNavController().navigate(
                        ResetYourPasswordFragmentDirections.actionResetYourPasswordFragmentToPasswordRecoverySuccessfulFragment(),
                        customNavAnimation().build()
                    )
                    Toast.makeText(requireContext(), getString(R.string.success_text), Toast.LENGTH_SHORT).show()
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