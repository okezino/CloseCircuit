package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.data.network.models.Budget
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanBudgetBinding
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.adapters.BudgetsAdapter
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels.AllStepsViewModel

class CreatePlanBudgetFragment : Fragment(){
    private var _binding: FragmentCreatePlanBudgetBinding? = null
    private val binding get() = _binding!!
    private lateinit var budgetsAdapter: BudgetsAdapter
    private lateinit var budgetsRecyclerView: RecyclerView
    private val allStepsViewModel: AllStepsViewModel by activityViewModels()
    private var currentBudgetList: ArrayList<Budget> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreatePlanBudgetBinding.inflate(inflater, container, false)
        budgetsRecyclerView = binding.budgetItemRecyclerView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        budgetsAdapter = BudgetsAdapter()

        val infoButton = binding.contentInfo
        val stepsInfo = binding.infoDialogLayout
        val infoTitle = stepsInfo.infoDialogTitle
        val infoDescription = stepsInfo.infoDialogDescription
        val infoCloseButton = stepsInfo.closeDialogIcon

        infoTitle.text = getString(R.string.budget_info_title)
        infoDescription.text = getString(R.string.budget_info_description)

        infoCloseButton.setOnClickListener {
            stepsInfo.infoItemLayout.visibility = View.INVISIBLE
        }

        infoButton.setOnClickListener {
            if (stepsInfo.infoItemLayout.isVisible) {
                stepsInfo.infoItemLayout.visibility = View.INVISIBLE
            } else {
                stepsInfo.infoItemLayout.visibility = View.VISIBLE
            }
        }

        observeBudgetsList()
        setUpBudgetsAdapter()
    }

    override fun onResume() {
        observeBudgetsList()
        budgetsAdapter.submitList(currentBudgetList)
        super.onResume()
    }

    private fun setUpBudgetsAdapter() {
        budgetsAdapter = BudgetsAdapter()
        budgetsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        budgetsRecyclerView.adapter = budgetsAdapter
        budgetsRecyclerView.visibility = View.VISIBLE
        binding.noBudgetYetTextView.visibility = View.GONE
    }

    private fun observeBudgetsList() {
        allStepsViewModel.getBudgetResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val budgetsForPlan = arrayListOf<Budget>()
                    for (budget in allStepsViewModel.getBudgetResponse.value?.datas?.data!!.budgets) {
                        if (budget.plan == allStepsViewModel.planId) {
                            budgetsForPlan.add(budget)
                        }
                    }
                    currentBudgetList = budgetsForPlan
                    allStepsViewModel.budgetsForPlan = budgetsForPlan
                    budgetsAdapter.submitList(budgetsForPlan)
                    budgetsAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
