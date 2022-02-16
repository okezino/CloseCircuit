package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentEditEmailVerificationScreenBinding

class EditEmailVerificationScreenFragment : Fragment(R.layout.fragment_edit_email_verification_screen) {
    private var _binding: FragmentEditEmailVerificationScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditEmailVerificationScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentLoginLoginBtn.setOnClickListener {
            findNavController().navigate(EditEmailVerificationScreenFragmentDirections.actionEditEmailVerificationScreenFragmentToEmailVerificationFragment(), customNavAnimation().build())
        }
        binding.closeIcon.setOnClickListener {
            findNavController().navigate(EditEmailVerificationScreenFragmentDirections.actionEditEmailVerificationScreenFragmentToEmailVerificationFragment(),customNavAnimation().build())
        }
    }
}
