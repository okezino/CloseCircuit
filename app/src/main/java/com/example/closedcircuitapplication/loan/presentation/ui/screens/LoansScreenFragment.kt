package com.example.closedcircuitapplication.loan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.presentation.ui.adapter.OnBoardingItemAdapter
import com.example.closedcircuitapplication.authentication.utils.DataFactory
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.dashboard.presentation.ui.screens.ProfileFragmentDirections
import com.example.closedcircuitapplication.databinding.FragmentLoansScreenBinding
import com.example.closedcircuitapplication.loan.presentation.models.LoanStatus
import com.example.closedcircuitapplication.loan.presentation.ui.adapters.LoanViewPagerAdapter

class LoansScreenFragment : Fragment() {

    private var _binding: FragmentLoansScreenBinding? = null
    private val binding get() = _binding!!
    private val loanItems = mutableListOf<LoanStatus>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoansScreenBinding.inflate(inflater, container, false)

        initData()
        initView()

        binding.loanSchedule.setOnClickListener {
            findNavController().navigate(
                LoansScreenFragmentDirections.actionLoansScreenFragmentToLoanScheduleFragment(),
                customNavAnimation().build()
            )
        }

        return binding.root

    }

    private fun initData(){
        loanItems.add(LoanStatus("Loan Disbursed", "NGN 200, 000"))
        loanItems.add(LoanStatus("Next Repayment", "NGN 30, 000"))
    }


    private fun initView(){
        val adapter = LoanViewPagerAdapter(loanItems)
        binding.loanViewPager.adapter = adapter
    }

}