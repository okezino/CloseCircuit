package com.example.closedcircuitapplication.support.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.databinding.FragmentPaymentsSupportBinding


class PaymentsSupportFragment : Fragment() {
    private var _binding: FragmentPaymentsSupportBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentsSupportBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}