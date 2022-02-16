package com.example.closedcircuitapplication.sponsors.presentation.Screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.Validation
import com.example.closedcircuitapplication.databinding.FragmentUsersEditProfileBinding


class UsersEditProfileFragment : Fragment() {

    private var _binding:FragmentUsersEditProfileBinding? =  null
    private val binding get() = _binding!!
    private lateinit var  fullName :String
    private lateinit var username :String
    private lateinit var emailAddress : String
    private lateinit var phone_number : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUsersEditProfileBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentUserEditProfileSaveChangesBtn.setOnClickListener {
            fullName = binding.fragmentEditProfileFullnameEditText.text.toString()
            username = binding.fragmentEditProfileUsernameEditText.text.toString()
            emailAddress = binding.fragmentUserEditProfileEmailAddressEditText.text.toString()
            phone_number = binding.fragmentEditProfilePhoneNumberEditText.text.toString()

            if (Validation.validateUserEditProfileTextInputField(
                    fullName, username, emailAddress, phone_number)
            ) {
                Toast.makeText(requireContext(), " fill all the require fields", Toast.LENGTH_SHORT).show()
            } else{
                //TODO (NAVIGATE)
            }

        }
    }



}