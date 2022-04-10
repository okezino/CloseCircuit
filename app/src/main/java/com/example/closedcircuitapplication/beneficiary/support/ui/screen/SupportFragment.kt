package com.example.closedcircuitapplication.beneficiary.support.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentSupportBinding


class SupportFragment : Fragment() {
  private var _binding:FragmentSupportBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSupportBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            howToUseLinearlayout.setOnClickListener {
                findNavController().navigate(SupportFragmentDirections.actionSupportFragmentToHowToUseSupportFragment(), customNavAnimation().build())
            }
            paymentsLinearLayoutSupportFragment.setOnClickListener {
                findNavController().navigate(SupportFragmentDirections.actionSupportFragmentToPaymentsSupportFragment(),customNavAnimation().build())
            }

            complaintsAndIssuesTv.setOnClickListener {
                findNavController().navigate(SupportFragmentDirections.actionSupportFragmentToComplaintAndIssuesFragment(),customNavAnimation().build())
            }
        }
    }
}