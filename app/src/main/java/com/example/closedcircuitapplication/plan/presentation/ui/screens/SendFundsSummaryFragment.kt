package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.databinding.FragmentSendFundsSummaryBinding
import java.util.*

class SendFundsSummaryFragment : Fragment() {
    private var _binding: FragmentSendFundsSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSendFundsSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fundsAvailableTextView.text = "NGN 0.00"
        binding.nextStepTextView.text = "Land purchase"
        binding.bankNameTextView.text = "Zenith".uppercase(Locale.getDefault())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
