package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentEmailVerificationBinding
import com.example.closedcircuitapplication.databinding.FragmentProjectScreenBinding
import com.example.closedcircuitapplication.databinding.FragmentSuccessfulEmailVerificationScreenBinding

class SuccessfulEmailVerificationScreenFragment : Fragment(R.layout.fragment_successful_email_verification_screen) {
    private var _binding: FragmentSuccessfulEmailVerificationScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessfulEmailVerificationScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.proceedBtn.setOnClickListener {
            findNavController().navigate(SuccessfulEmailVerificationScreenFragmentDirections.actionSuccessfulEmailVerificationScreenFragmentToCreateAPlanFragment())
        }
    }
}