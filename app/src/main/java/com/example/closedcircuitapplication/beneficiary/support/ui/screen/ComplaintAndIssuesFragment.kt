package com.example.closedcircuitapplication.beneficiary.support.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentComplaintAndIssuesBinding


class ComplaintAndIssuesFragment : Fragment() {
    private var _binding: FragmentComplaintAndIssuesBinding? =null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentComplaintAndIssuesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.speakToCustomerCareLinearLayout.setOnClickListener {
            findNavController().navigate(ComplaintAndIssuesFragmentDirections.actionComplaintAndIssuesFragmentToCustomerCareFragment(), customNavAnimation().build())
        }
    }
}