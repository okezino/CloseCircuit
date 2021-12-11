package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentResetYourPasswordBinding


class ResetYourPasswordFragment : Fragment() {
    var _binding:FragmentResetYourPasswordBinding?= null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResetYourPasswordBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToPasswordRecoverySuccessfulFragment()
    }

    // this used to navigate to PasswordRecoverySuccessfulFragment
    fun navigateToPasswordRecoverySuccessfulFragment(){
        binding.restYourPasswordResetPasswordBtn.setOnClickListener {
            findNavController().navigate(R.id.action_resetYourPasswordFragment_to_passwordRecoverySuccessfulFragment)

        }
    }
}