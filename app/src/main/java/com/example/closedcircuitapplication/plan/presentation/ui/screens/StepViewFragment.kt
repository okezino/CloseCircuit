package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentStepViewBinding
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem

class StepViewFragment : Fragment() {
    private lateinit var budgetData: StepsBudgetItem
    private var _binding: FragmentStepViewBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStepViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        budgetData = arguments?.getParcelable("budgetData")!!
        binding.myStepTargetAmountValueTextView.text = budgetData.targetFunds
        binding.myStepsFundsRaisedValueTextView.text = budgetData.totalFundsRaised
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.stepViewFragmentToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.step_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}