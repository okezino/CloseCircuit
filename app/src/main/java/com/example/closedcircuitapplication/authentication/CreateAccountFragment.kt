package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import android.text.Editable
import android.text.method.TextKeyListener.clear
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.Validation
import com.example.closedcircuitapplication.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    lateinit var countryCode: String
    lateinit var password:String

    val uppercase = Regex("[A-Z]")
    val lowercase = Regex("[a-z]")
    val digitCharackter = Regex("[0-9]")
    val specailCharacter = Regex("[@#\$%^&+=*_-]")

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

        binding.createAccountBtn.setOnClickListener {
            val fullNmae = binding.fullNameTextInput.text.toString().trim()
            val phoneNumber = binding.phoneNumberTextInput.text.toString().trim()
            val email = binding.emailTextInput.text.toString().trim()
            password = binding.passwordTextInput.text.toString().trim()
            val comfirmPassword = binding.comfirmPasswordTextInput.text.toString().trim()

            // create a user account
            createAcount( fullNmae, phoneNumber, password, email, comfirmPassword, view)
        }
        binding.countrycode.setOnCountryChangeListener {
            countryCode = binding.countrycode.selectedCountryCodeWithPlus
        }

        binding.passwordTextInput.addTextChangedListener {
            password = binding.passwordTextInput.text.toString()
            passwordInputValidation(password)
        }
        binding.loginTv.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment2)
        }
    }


    fun createAcount(
        fullName :String,
        phoneNumber :String,
        password: String,
        email: String,
        comfirmPassword: String,
        view: View
    ) {
        // check if the full name is entered and is valide name
        if(fullName.isEmpty() || fullName.length < 4 || fullName.isDigitsOnly()){
            binding.fullNameTextInput.error = "Enter your name"
        } else if (!Validation.validateEmailInput(email.toString())) {
                binding.emailTextInput.error
                binding.wrongEmailWorningTv.visibility = View.VISIBLE
                binding.wrongEmailWorningTv.setTextColor(
                    resources.getColor(R.color.red))
            }else if (phoneNumber.length < 9){
            binding.wrongEmailWorningTv.visibility = View.GONE
            binding.phoneNumberTextInput.error ="Incorrect number"
        } else {

                // check if the password provided is the same with the confirm password
                 if (password.isEmpty() || password != comfirmPassword) {
                     binding.comfirmPasswordTextInput.error = "Password does not match"
                } else {
                    findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment2)
                }
            }
    }

    // check the password input if it meet all the requirement
    fun passwordInputValidation(password: String) {

        if (password.length <= 7) {
            binding.Maximuimof8CharaterTv.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.Maximuimof8CharaterTv.setTextColor(resources.getColor(R.color.green_700))
        }
        if (!password.contains(uppercase) || !password.toString().contains(lowercase)) {
            binding.UppercaseAndLowercaseTv.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.UppercaseAndLowercaseTv.setTextColor(resources.getColor(R.color.green_700))
        }
        if (!password.contains(digitCharackter)) {
            binding.NumbersTv.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.NumbersTv.setTextColor(resources.getColor(R.color.green_700))
        }
        if (!password.contains(specailCharacter)) {
            binding.spacialCharacterTv.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.spacialCharacterTv.setTextColor(resources.getColor(R.color.green_700))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}