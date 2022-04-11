package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.ui.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.databinding.FragmentSampleDashboardBinding

class SampleDashboardFragment : Fragment() {
    private  var _binding: FragmentSampleDashboardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSampleDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentSampleTv.setOnClickListener {
            findNavController().navigate(SampleDashboardFragmentDirections.actionSampleDashboardFragment2ToSponsorFundingLevelFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}