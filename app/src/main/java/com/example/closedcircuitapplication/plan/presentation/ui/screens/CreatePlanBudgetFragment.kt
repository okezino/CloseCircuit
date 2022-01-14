package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.dashboard.interfaces.ClickListener
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.StepsBudgetsAdapter
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanBudgetBinding

class CreatePlanBudgetFragment : Fragment(), ClickListener {
    private var _binding: FragmentCreatePlanBudgetBinding? = null
    private val binding get() = _binding!!

    private lateinit var budgetsAdapter: StepsBudgetsAdapter
    private lateinit var budgetsRecyclerView: RecyclerView

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

        val infoButton = binding.contentInfo
        val stepsInfo = binding.infoDialogLayout
        val infoTitle = stepsInfo.infoDialogTitle
        val infoDescription = stepsInfo.infoDialogDescription
        val infoCloseButton = stepsInfo.closeDialogIcon
        val createBudgetsButton = binding.addItemLayout

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

        createBudgetsButton.setOnClickListener {
            findNavController().navigate(R.id.createBudgetFragment)
        }

        getProjectBudgets()
    }

    private fun getProjectBudgets() {
        val budgetSteps = ArrayList<StepsBudgetItem>()
        budgetSteps.add(StepsBudgetItem("Frontend Development", "NGN 0.00", "NGN 0.00"))
        budgetSteps.add(StepsBudgetItem("UI/UX Design", "NGN 0.00", "NGN 0.00"))
        budgetSteps.add(StepsBudgetItem("Backend Development", "NGN 0.00", "NGN 0.00"))

        budgetsAdapter = StepsBudgetsAdapter(budgetSteps, this)

        budgetsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        budgetsRecyclerView.adapter = budgetsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(budgetDate: StepsBudgetItem) {
        TODO("Not yet implemented")
    }
}
