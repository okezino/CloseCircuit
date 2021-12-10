package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.Validation
import com.example.closedcircuitapplication.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    lateinit var countryCode :String

    val uppercase= Regex("[A-Z]")
    val  lowercase= Regex("[a-z]")
    val digitCharackter= Regex("[0-9]")
    val specailCharacter= Regex("[@#\$%^&+=*_-]")

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

        val fullName = binding.fullNameTextInput.text
        val phoneNumber = binding.phoneNumberTextInput.text
        val email = binding.emailTextInput.text
        val password = binding.passwordTextInput.text
        val comfirmPassword = binding.comfirmPasswordTextInput.text

        binding.countrycode.setOnCountryChangeListener {
            countryCode = binding.countrycode.selectedCountryCodeWithPlus

        }

        binding.createAccountBtn.setOnClickListener {

            Toast.makeText(context, fullName, Toast.LENGTH_SHORT).show()
            Toast.makeText(context, email.toString(), Toast.LENGTH_SHORT).show()
            Toast.makeText(context, password, Toast.LENGTH_SHORT).show()
            Toast.makeText(context, phoneNumber, Toast.LENGTH_SHORT).show()
            Toast.makeText(context, countryCode, Toast.LENGTH_SHORT).show()

            // check if the email address is correct or not
            if (!Validation.validateEmailInput(email.toString())) {
                binding.wrongEmailWorningTv.visibility = View.VISIBLE
                binding.wrongEmailWorningTv.setTextColor(
                    resources.getColor(R.color.red)
                )
            } else {
                binding.wrongEmailWorningTv.visibility = View.GONE
            }

            // check if the passworde provide is the same with the comfirm password
            if (password.toString() != comfirmPassword.toString()){
                Toast.makeText(context, "password does not match", Toast.LENGTH_SHORT).show()
            }
       }
        // check the password input if it meet all the requirement
        binding.passwordTextInput.addTextChangedListener {
            if (password.toString().length <= 7){
                binding.Maximuimof8CharaterTv.setTextColor(resources.getColor(R.color.red))
            }else{
                binding.Maximuimof8CharaterTv.setTextColor(resources.getColor(R.color.green_700))
            }
            if (!password.toString().contains(uppercase) || !password.toString().contains(lowercase)){
                binding.UppercaseAndLowercaseTv.setTextColor(resources.getColor(R.color.red))
            }else{
                binding.UppercaseAndLowercaseTv.setTextColor(resources.getColor(R.color.green_700))
            }
            if (!password.toString().contains(digitCharackter)){
                binding.NumbersTv.setTextColor(resources.getColor(R.color.red))
            }else{
                binding.NumbersTv.setTextColor(resources.getColor(R.color.green_700))
            }
            if (!password.toString().contains(specailCharacter)){
                binding.spacialCharacterTv.setTextColor(resources.getColor(R.color.red))
            }else{
                binding.spacialCharacterTv.setTextColor(resources.getColor(R.color.green_700))
            }
        }

        binding.registerUsingGoogleBtn.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_verifyEmailFragment)

        }

    }
}