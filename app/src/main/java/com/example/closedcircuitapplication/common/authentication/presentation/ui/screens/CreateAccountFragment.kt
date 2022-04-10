package com.example.closedcircuitapplication.common.authentication.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.authentication.presentation.ui.viewmodels.AuthenticationViewModel
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.common.common.utils.Validation
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentCreateAccountBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var countryCode: String
    lateinit var password: String
    lateinit var email: String
    lateinit var fullName: String
    private lateinit var phoneNumber: String
    private lateinit var confirmPassword: String
    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //navigate back to  welcome screen from create account screen

        initObservers()
        with(binding) {
            createAccountBtn.setOnClickListener {

                fullName = fullNameTextInput.text.toString().trim()
                phoneNumber = phoneNumberTextInput.text.toString().trim()
                email = emailTextInput.text.toString().trim()
                password = passwordTextInput.text.toString().trim()
                confirmPassword = comfirmPasswordTextInput.text.toString().trim()
                countryCode = fragmentCreateAccountCountrycodePicker.selectedCountryCodeWithPlus

                // create a user account
                isAllFieldsValidated(RegisterRequest(email, fullName, "Beneficiary",countryCode + phoneNumber, password,  confirmPassword))
            }

            fullNameTextInput.addTextChangedListener {
                fullName = binding.fullNameTextInput.text.toString().trim()
                fullNameTExtInputValidation(fullName)
            }
            phoneNumberTextInput.addTextChangedListener {
                phoneNumber = binding.phoneNumberTextInput.text.toString().trim()
                onNumberTextInputChangeListener(phoneNumber)
            }

            emailTextInput.addTextChangedListener {
                email = binding.emailTextInput.text.toString().trim()
                onEmailTextInputChangeListener(email)
            }
            passwordTextInput.addTextChangedListener {
                password = binding.passwordTextInput.text.toString()
                passwordInputValidationListener(password)
            }
            loginTv.setOnClickListener {
                findNavController().navigate(
                    CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment(),
                    customNavAnimation().build()
                )
            }
        }
    }

    
    private fun isAllFieldsValidated(accountData: RegisterRequest){
        val errors = Validation.validateAccountData(accountData)
        with(binding) {
               when {
                   errors.contains("Enter your full name") -> fullNameTextInput.error = "Enter your full name"
                   errors.contains("cant be empty") -> emailTextInput.error
                   errors.contains("Incomplete number") -> phoneNumberTextInput.error = "Incomplete number"
                   errors.contains("Password does not match") -> comfirmPasswordTextInput.error = "Password does not match"
                   else -> viewModel.register(accountData)
               }
       }
    }

    private fun fullNameTExtInputValidation(fullName:String){
        val errorsList = listOf(
            "Can't start with numbers",
            "must not contain special characters")
        val result = Validation.validateFullNameInput(fullName)
        for (error in result){
            if (errorsList.contains(error))
                binding.fullNameTextInput.error = error
        }
    }

    private fun onEmailTextInputChangeListener(email: String) {
        if (!Validation.validateEmailInput(email)) {
            binding.emailTextInput.error
            binding.wrongEmailWorningTv.visibility = View.VISIBLE
            binding.wrongEmailWorningTv.setTextColor(
                ContextCompat.getColor(requireContext(),R.color.red)
            )
        } else {
            binding.wrongEmailWorningTv.visibility = View.GONE
        }
    }

    private fun onNumberTextInputChangeListener(number: String) {
        if (Validation.validatePhone_number(number)) {
            binding.wrongEmailWorningTv.visibility = View.GONE
            binding.phoneNumberTextInput.error = "Incomplete number"
        }
    }

    // check the password input if it meet all the requirement
    private fun passwordInputValidationListener(password: String) {

        val validatePassword = Validation.validatePasswordErrors(password)

        val fieldsArray = mutableListOf(
            binding.minimuimof8CharaterTv,
            binding.UppercaseAndLowercaseTv,
            binding.NumbersTv,
            binding.spacialCharacterTv
        )

        for (field in fieldsArray) {
            if (validatePassword.contains(field.text)) {
                field.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            } else {
                field.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_700))
            }
        }
    }

    private fun initObservers(){
        viewModel.registerResult.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    findNavController().navigate(
                        CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment(),
                        customNavAnimation().build()
                    )
                    Snackbar.make(binding.root, "Successful", Snackbar.LENGTH_LONG)
                        .show()
                }
                is Resource.Error -> {
                    Snackbar.make(binding.root, resource.message, Snackbar.LENGTH_LONG)
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