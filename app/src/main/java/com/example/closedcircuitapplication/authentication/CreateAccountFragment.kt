package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.Validation
import com.example.closedcircuitapplication.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    lateinit var countryCode: String

    val uppercase = Regex("[A-Z]")
    val lowercase = Regex("[a-z]")
    val digitCharackter = Regex("[0-9]")
    val specailCharacter = Regex("[@#\$%^&+=*_-]")
    lateinit var password: TextView

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

        val email = binding.emailTextInput.text
        val password = binding.passwordTextInput.text
        val comfirmPassword = binding.comfirmPasswordTextInput.text

        binding.countrycode.setOnCountryChangeListener {
            countryCode = binding.countrycode.selectedCountryCodeWithPlus
        }

        binding.passwordTextInput.addTextChangedListener {
            createAcount(password, email, comfirmPassword, view)
        }
    }

    fun createAcount(
        password: Editable?,
        email: Editable?,
        comfirmPassword: Editable?,
        view: View
    ) {
        passwordInputValidation(password)
        binding.createAccountBtn.setOnClickListener {
            // check if the email address is correct or not
            if (!Validation.validateEmailInput(email.toString())) {
                binding.wrongEmailWorningTv.visibility = View.VISIBLE
                binding.wrongEmailWorningTv.setTextColor(
                    resources.getColor(R.color.red)
                )
            } else {
                binding.wrongEmailWorningTv.visibility = View.GONE
                // check if the passworde provide is the same with the comfirm password

                if (password.toString() != comfirmPassword.toString()) {
                    Toast.makeText(context, "password does not match", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    findNavController().navigate(R.id.action_createAccountFragment_to_verifyEmailFragment)
                }
            }
        }
    }

    // check the password input if it meet all the requirement
    fun passwordInputValidation(password: Editable?) {

        if (password.toString().length <= 7) {
            binding.Maximuimof8CharaterTv.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.Maximuimof8CharaterTv.setTextColor(resources.getColor(R.color.green_700))
        }
        if (!password.toString().contains(uppercase) || !password.toString().contains(lowercase)) {
            binding.UppercaseAndLowercaseTv.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.UppercaseAndLowercaseTv.setTextColor(resources.getColor(R.color.green_700))
        }
        if (!password.toString().contains(digitCharackter)) {
            binding.NumbersTv.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.NumbersTv.setTextColor(resources.getColor(R.color.green_700))
        }
        if (!password.toString().contains(specailCharacter)) {
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