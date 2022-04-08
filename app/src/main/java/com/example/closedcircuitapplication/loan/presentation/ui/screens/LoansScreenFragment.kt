package com.example.closedcircuitapplication.loan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.handleBackPress
import com.example.closedcircuitapplication.common.utils.popBackStack
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
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleBackPress()
        binding.fragmentLoanScreenToolbarBackArrowIv.setOnClickListener { popBackStack() }
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