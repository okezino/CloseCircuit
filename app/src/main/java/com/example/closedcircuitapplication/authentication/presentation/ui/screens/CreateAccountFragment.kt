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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    lateinit var countryCode: String


    lateinit var password: String
    lateinit var email: String
    lateinit var fullName: String
    lateinit var phoneNumber: String
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
            phoneNumber = binding.phoneNumberTextInput.text.toString().trim()
            email = binding.emailTextInput.text.toString().trim()
            password = binding.passwordTextInput.text.toString().trim()
            confirmPassword = binding.comfirmPasswordTextInput.text.toString().trim()

            // create a user account
            createAcount(fullName, phoneNumber, password, email, confirmPassword)
        }

        binding.countrycode.setOnCountryChangeListener {
            countryCode = binding.countrycode.selectedCountryCodeWithPlus
        }

        binding.fullNameTextInput.addTextChangedListener {
            fullName = binding.fullNameTextInput.text.toString().trim()
            fullNameTExtInputValidation(fullName)
        }
        binding.phoneNumberTextInput.addTextChangedListener {
            phoneNumber = binding.phoneNumberTextInput.text.toString().trim()
            onNumberTextInputChangeListener(phoneNumber)
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
            findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment, null,
                customNavAnimation().build())
        }
    }


    fun createAcount(
        fullName: String,
        phoneNumber: String,
        password: String,
        email: String,
        comfirmPassword: String,
    ) {
        // check if the full name is entered and is a valid name
        if(fullName.isBlank()){
            binding.fullNameTextInput.error = "cant be empty"
        }else if (!Validation.validateEmailInput(email) || email.isEmpty() ) {
            binding.emailTextInput.error = "cant be empty"
        } else if (Validation.validatePhone_number(phoneNumber)){
            binding.phoneNumberTextInput.error = "Incomplete number"
        } else if (password.isEmpty() || password != comfirmPassword) {
            binding.comfirmPasswordTextInput.error = "Password does not match"
        } else {

            //  this is where the viewModel is instantiated and made a network request
            viewModel.register(RegisterRequest(email, fullName, "Beneficiary", phoneNumber, password, confirmPassword))

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
                        R.id.action_createAccountFragment_to_loginFragment, null,
                        customNavAnimation().build()
                    )

                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
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