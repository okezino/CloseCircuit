package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentAccountRegistrationBinding
import com.example.closedcircuitapplication.databinding.FragmentCreateAccountBinding


class AccountRegistrationFragment : Fragment() {

    private var _binding: FragmentAccountRegistrationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccountRegistrationBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}