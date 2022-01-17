package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.Validation
import com.example.closedcircuitapplication.databinding.FragmentResetYourPasswordBinding
import com.google.android.material.snackbar.Snackbar


class ResetYourPasswordFragment : Fragment() {
    private var _binding: FragmentResetYourPasswordBinding? = null
    private val binding get() = _binding!!

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

        binding.newPasswordTv.addTextChangedListener(buttonHandler)
        binding.confirmPasswordTv.addTextChangedListener(buttonHandler)

        resetPassword()

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

            if (Validation.validatePasswordPattern(newPassword)) {
                if (Validation.validatePasswordPattern(confirmPassword)) {
                    if (newPassword == confirmPassword) {
                        findNavController().navigate(R.id.action_resetYourPasswordFragment_to_passwordRecoverySuccessfulFragment)
                    } else {
                        Snackbar.make(
                            binding.root,
                            "Passwords must match",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Snackbar.make(
                        binding.root,
                        "Invalid password",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            } else {
                Snackbar.make(binding.root, "Invalid password", Snackbar.LENGTH_LONG).show()
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}