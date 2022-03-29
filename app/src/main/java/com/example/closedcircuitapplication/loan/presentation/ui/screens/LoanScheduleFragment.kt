package com.example.closedcircuitapplication.loan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentLoanScheduleBinding
import com.example.closedcircuitapplication.databinding.FragmentLoansScreenBinding

class LoanScheduleFragment : Fragment() {

    private var _binding: FragmentLoanScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoanScheduleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initView()

    }

    private fun initView(){
        val meansOfSupport = resources.getStringArray(R.array.support_means)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, meansOfSupport)
        binding.meansOfSupportDropdown.setAdapter(arrayAdapter)

        val maxNumberOfLenders = resources.getStringArray(R.array.total_number_of_lenders)
        val selectMaxNumberOfLenders =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, maxNumberOfLenders)
        binding.numberOfLendersDropdown.setAdapter(selectMaxNumberOfLenders)

        val graceDuration = resources.getStringArray(R.array.grace_duration)
        val selectGraceDuration = ArrayAdapter(requireContext(), R.layout.dropdown_item, graceDuration)
        binding.graceDurationDropdown.setAdapter(selectGraceDuration)

        val repaymentDuration = resources.getStringArray(R.array.repayment_duration)
        val selectRepaymentDuration = ArrayAdapter(requireContext(), R.layout.dropdown_item, repaymentDuration)
        binding.repaymentDurationDropdown.setAdapter(selectRepaymentDuration)

        val loanInterestRate = resources.getStringArray(R.array.loan_interest)
        val selectLoanInterestRate = ArrayAdapter(requireContext(), R.layout.dropdown_item, loanInterestRate)
        binding.loanInterestDropdown.setAdapter(selectLoanInterestRate)
    }

}