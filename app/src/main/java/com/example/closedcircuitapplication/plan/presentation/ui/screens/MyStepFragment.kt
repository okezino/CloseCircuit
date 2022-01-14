package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.MyStepsBudgetAdapter
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem
import com.example.closedcircuitapplication.databinding.FragmentMyStepBinding
import com.example.closedcircuitapplication.utils.budgetList


class MyStepFragment : Fragment() {
    private var _binding: FragmentMyStepBinding? = null
    private val binding get() = _binding!!
    val budgetAdapter = MyStepsBudgetAdapter()
    private lateinit var budgetData: StepsBudgetItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        budgetData = arguments?.getParcelable<StepsBudgetItem>("budgetData")!!
        budgetAdapter.submitList(budgetList)
        manageUiView()
        binding.myStepsRecyclerView.apply {
            adapter = budgetAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

   private fun manageUiView() {
        if (budgetData != null) {
            binding.myStepTargetAmountValueTextView.text = budgetData.targetFunds
            binding.myStepsFundsRaisedValueTextView.text = budgetData.totalFundsRaised
        }

       if (binding.myStepTargetAmountValueTextView.text == binding.myStepsFundsRaisedValueTextView.text) {
           binding.myStepStepFundedCheckMarkImageView.visibility = View.VISIBLE
           binding.myStepDeleteIconImageView.setImageResource(R.drawable.ic_blurred_trash)
           binding.myStepsEditIconImageView.setImageResource(R.drawable.ic_blurred_edit)
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}