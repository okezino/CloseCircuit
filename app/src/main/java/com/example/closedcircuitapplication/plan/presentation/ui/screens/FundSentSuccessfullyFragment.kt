package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentFundSentSuccessfullyBinding


class FundSentSuccessfullyFragment : Fragment(R.layout.fragment_fund_sent_successfully) {
    private lateinit var binding: FragmentFundSentSuccessfullyBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFundSentSuccessfullyBinding.bind(view)
    }
}