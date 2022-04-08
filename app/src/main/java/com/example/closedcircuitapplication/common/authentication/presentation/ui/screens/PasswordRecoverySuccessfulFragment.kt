package com.example.closedcircuitapplication.common.authentication.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentPasswordRecoverySuccessfulBinding


class PasswordRecoverySuccessfulFragment : Fragment() {
    private var _binding:FragmentPasswordRecoverySuccessfulBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPasswordRecoverySuccessfulBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.passwordRecoverySuccessProceedButtpn.setOnClickListener {
            findNavController().navigate(
                PasswordRecoverySuccessfulFragmentDirections.actionPasswordRecoverySuccessfulFragmentToLoginFragment(),
                customNavAnimation().build())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}