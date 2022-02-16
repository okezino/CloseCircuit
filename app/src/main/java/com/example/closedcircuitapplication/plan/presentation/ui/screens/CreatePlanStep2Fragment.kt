package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentCreateAPlanBinding
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanStep2Binding

class CreatePlanStep2Fragment : Fragment() {
    private var _binding: FragmentCreatePlanStep2Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreatePlanStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

}