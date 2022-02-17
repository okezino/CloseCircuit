package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentPlanCreatedSucessfullyBinding

class PlanCreatedSuccessfullyFragment : Fragment() {

    private var _binding: FragmentPlanCreatedSucessfullyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPlanCreatedSucessfullyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.planSuccessfullyCreatedProceedBtn.setOnClickListener {
            findNavController().navigate(
                PlanCreatedSuccessfullyFragmentDirections.actionPlanCreatedSuccessfullyFragmentToCreatePlanFragment(),
                customNavAnimation().build()
            )
        }
    }
}