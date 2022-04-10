package com.example.closedcircuitapplication.beneficiary.support.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.closedcircuitapplication.databinding.FragmentCustomerCareBinding


class CustomerCareFragment : Fragment() {
    private var _binding: FragmentCustomerCareBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCustomerCareBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.attachFileIV.setOnClickListener {
            Toast.makeText(requireContext(), "attach a file", Toast.LENGTH_SHORT).show()
        }

        binding.chatSendBtn.setOnClickListener { Toast.makeText(requireContext(), "sending message", Toast.LENGTH_SHORT).show() }
    }
}