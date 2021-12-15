package com.example.closedcircuitapplication.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentAccountBinding
import com.example.closedcircuitapplication.databinding.FragmentCreateAccountBinding

class AccountFragment : Fragment() {
    private var _binding:FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentAccountBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToForgetPasswordFragment()
    }

    fun navigateToForgetPasswordFragment(){
        binding.accountTv.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_forgotPasswordFragment)
        }
    }
}
