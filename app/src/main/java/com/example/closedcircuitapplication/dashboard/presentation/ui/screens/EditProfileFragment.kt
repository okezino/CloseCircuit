package com.example.closedcircuitapplication.dashboard.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentEditProfileBinding
import com.example.closedcircuitapplication.databinding.FragmentProfileBinding
import com.example.closedcircuitapplication.plan.presentation.ui.screens.EditPlanFragmentArgs

class EditProfileFragment : Fragment() {

    private  var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val args: EditProfileFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            fullNameTextInput.setText(args.fullName)
            phoneNumberTextInput.setText(args.phoneNumber)
            selectCountryCategoryDropdown.setText(args.nationality)
        }
    }
}