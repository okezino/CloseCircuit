package com.example.closedcircuitapplication.ui.myBudget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.dashboard.adapter.MyBudgetSponsorsAdapter
import com.example.closedcircuitapplication.dashboard.models.StepsBudgetItem
import com.example.closedcircuitapplication.databinding.FragmentMyBudgetBinding
import com.example.closedcircuitapplication.utils.sponsorsList

class MyBudgetFragment : Fragment() {
    private var _binding: FragmentMyBudgetBinding? = null
    private val binding get() = _binding!!
    private val sponsorsAdapter = MyBudgetSponsorsAdapter()
    private lateinit var budgetData: StepsBudgetItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        budgetData = arguments?.getParcelable<StepsBudgetItem>("budgetData")!!
        sponsorsAdapter.submitList(sponsorsList)
        binding.myBudgetRecyclerView.apply {
            adapter = sponsorsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        binding.myBudgetBudgetNameTextView.text = budgetData.itemTitle
        binding.myBudgetTargetBudgetAmountTextView.text = budgetData.targetFunds
        binding.myBudgetAmountRaisedAmountTextView.text = budgetData.totalFundsRaised
        binding.myBudgetNoSponsorsYetTextView.visibility = View.GONE
        binding.myBudgetRecyclerView.visibility = View.VISIBLE
        binding.myBudgetSponsorsTextView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}