package com.example.closedcircuitapplication.authentication.presentation.ui.screens

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
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels.AuthenticationViewModel
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Validation
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentCreateAccountBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    lateinit var countryCode: String
    lateinit var password: String
    lateinit var email: String
    lateinit var fullName: String
    lateinit var phone_number: String
    lateinit var confirmPassword: String
    private val viewModel: AuthenticationViewModel by viewModels<AuthenticationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //navigate back to  welcome screen from create account screen

        initObservers()
        binding.createAccountBtn.setOnClickListener {
            fullName = binding.fullNameTextInput.text.toString().trim()
            phone_number = binding.phoneNumberTextInput.text.toString().trim()
            email = binding.emailTextInput.text.toString().trim()
            password = binding.passwordTextInput.text.toString().trim()
            confirmPassword = binding.comfirmPasswordTextInput.text.toString().trim()
            countryCode = binding.fragmentCreateAccountCountrycodePicker.selectedCountryCodeWithPlus

            // create a user account
            createAcount(fullName, countryCode+phone_number, password, email, confirmPassword)
        }

        binding.fullNameTextInput.addTextChangedListener {
            fullName = binding.fullNameTextInput.text.toString().trim()
            fullNameTExtInputValidation(fullName)
        }
        binding.phoneNumberTextInput.addTextChangedListener {
            phone_number = binding.phoneNumberTextInput.text.toString().trim()
            onNumberTextInputChangeListener(phone_number)
        }

        binding.emailTextInput.addTextChangedListener {
            email = binding.emailTextInput.text.toString().trim()
            onEmailTextInputChangeListener(email)
        }
        binding.passwordTextInput.addTextChangedListener {
            password = binding.passwordTextInput.text.toString()
            passwordInputValidationListener(password)
        }
        binding.loginTv.setOnClickListener {
            findNavController().navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment(),
                customNavAnimation().build())
        }
    }

    fun createAcount(
        fullName: String,
        phone_number: String,
        password: String,
        email: String,
        comfirmPassword: String,
    ) {
        // check if the full name is entered and is a valid name
        if(fullName.isBlank()){
            binding.fullNameTextInput.error = "cant be empty"
        }else if (!Validation.validateEmailInput(email) ) {
            binding.emailTextInput.error = "cant be empty"
        } else if (Validation.validatePhone_number(phone_number)){
            binding.phoneNumberTextInput.error = "Incomplete number"
        } else if (Validation.validatePassword_Equals_confirmPasswword(password, comfirmPassword)) {
            binding.comfirmPasswordTextInput.error = "Password does not match"
        } else {

            //  this is where the viewModel is instantiated and made a network request
            viewModel.register(RegisterRequest(email, fullName, "Beneficiary", phone_number, password, comfirmPassword))
        }
    }

    fun fullNameTExtInputValidation(fullName:String){
        val errorsList = listOf(
            "Can't start with numbers",
            "must not contain special characters")
        val result = Validation.validateFullNameInput(fullName)
        for (error in result){
            if (errorsList.contains(error))
                binding.fullNameTextInput.error = error
        }
    }

    fun onEmailTextInputChangeListener(email: String) {
        if (!Validation.validateEmailInput(email)) {
            binding.emailTextInput.error
            binding.wrongEmailWorningTv.visibility = View.VISIBLE
            binding.wrongEmailWorningTv.setTextColor(
                resources.getColor(R.color.red)
            )
        } else {
            binding.wrongEmailWorningTv.visibility = View.GONE
        }
    }

    fun onNumberTextInputChangeListener(number: String) {
        if (Validation.validatePhone_number(number)) {
            binding.wrongEmailWorningTv.visibility = View.GONE
            binding.phoneNumberTextInput.error = "Incomplete number"
        }
    }

    // check the password input if it meet all the requirement
    fun passwordInputValidationListener(password: String) {

        val validatePassword = Validation.validatePasswordErrors(password)

        val fieldsArray = mutableListOf(
            binding.minimuimof8CharaterTv,
            binding.UppercaseAndLowercaseTv,
            binding.NumbersTv,
            binding.spacialCharacterTv
        )

        for (field in fieldsArray) {
            if (validatePassword.contains(field.text)) {
                field.setTextColor(resources.getColor(R.color.red))
            } else {
                field.setTextColor(resources.getColor(R.color.green_700))
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
                    Snackbar.make(binding.root, "Successfull", Snackbar.LENGTH_LONG)
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