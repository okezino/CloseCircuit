package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
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

        //navigate from  login/create an account to loginScreen
        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_loginFragment)
        }

        //navigate from  login/create an account to  Create an account screen
        binding.welcomePageCreateAccountBtn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_createAccountFragment2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
