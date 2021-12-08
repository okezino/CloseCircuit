package com.example.closedcircuitapplication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

    }
}