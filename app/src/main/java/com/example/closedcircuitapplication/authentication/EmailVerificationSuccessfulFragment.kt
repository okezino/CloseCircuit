package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentEmailVerificationSuccessfulBinding
import com.example.closedcircuitapplication.databinding.FragmentVerifyEmailBinding


class EmailVerificationSuccessfulFragment : Fragment() {

 private var _binding:FragmentEmailVerificationSuccessfulBinding? = null
 private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmailVerificationSuccessfulBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.proceedBtn.setOnClickListener {
            findNavController().navigate(R.id.action_emailVerificationSuccessfulFragment_to_FirstFragment)
        }

    }

}