package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentWelcomeScreenBinding

class WelcomeScreenFragment : Fragment() {
    private var _binding: FragmentWelcomeScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // navigate from  welcome screen to login fragment
        binding.fragmentWelcomeLoginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_loginFragment, null,
                customNavAnimation().build())
        }
        // navigate from  welcome screen to create an account
        binding.welcomePageCreateAccountBtn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_createAccountFragment, null,
                customNavAnimation().build())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
