package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.domain.models.ResetPasswordRequest
import com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels.AuthenticationViewModel
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Validation
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentResetYourPasswordBinding
import com.google.android.material.snackbar.Snackbar
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
        _binding = FragmentResetYourPasswordBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun resetPassword(){
        binding.resetBtn.setOnClickListener {
            val newPassword = binding.newPasswordTv.text.toString().trim()
            val confirmPassword = binding.confirmPasswordTv.text.toString().trim()

            if(Validation.validatePasswordPattern(newPassword)){
                if (Validation.validatePasswordPattern(confirmPassword)){
                    if (newPassword == confirmPassword){
                        findNavController().navigate(ResetYourPasswordFragmentDirections.actionResetYourPasswordFragmentToPasswordRecoverySuccessfulFragment(),
                            customNavAnimation().build())
                        viewModel.resetPassword(ResetPasswordRequest(args.userEmail,args.otp, newPassword, confirmPassword
                        ))
                    }
                    else{
                        Snackbar.make(
                            binding.root,
                            "Passwords must match",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                else{
                    Snackbar.make(
                        binding.root,
                        "Invalid password",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            else{
                Snackbar.make(
                    binding.root,
                    "Invalid password",
                    Snackbar.LENGTH_LONG
                ).show()
            }


        }

    }

    private fun initObservers(){
        viewModel.resetPasswordResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    showPleaseWaitAlertDialog()
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    showPleaseWaitAlertDialog().dismiss()
                    findNavController().navigate(
                        R.id.passwordRecoverySuccessfulFragment
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


        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}