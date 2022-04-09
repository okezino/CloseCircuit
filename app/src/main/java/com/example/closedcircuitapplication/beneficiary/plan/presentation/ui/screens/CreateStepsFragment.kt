package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.common.utils.makeSnackBar
import com.example.closedcircuitapplication.common.common.utils.showPleaseWaitAlertDialog
import com.example.closedcircuitapplication.databinding.FragmentCreateStepsBinding
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.CreateBudgetRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.CreateStepsRequest
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.AddBudget
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.adapters.BudgetItemClicklistener
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.adapters.CreateBudgetAdapter
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels.StepsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateStepsFragment : Fragment(), BudgetItemClicklistener {
    private var _binding: FragmentCreateStepsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StepsViewModel by viewModels()
    private lateinit var planId: String
    private lateinit var pleaseWaitDialog: AlertDialog
    private val budgetsAdapter = CreateBudgetAdapter(this)

    @Inject
    lateinit var preferences: Preferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreateStepsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var weekAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.weeks,
            android.R.layout.simple_dropdown_item_1line
        )
        planId = arguments?.getString(getString(R.string.plan_id))!!
        binding.createStepStepDurationAutoCompleteTextView.apply {
            setAdapter(weekAdapter)
        }
        pleaseWaitDialog = showPleaseWaitAlertDialog()

        binding.createStepsAddBudgetTextView.setOnClickListener {
            showAddBudgetFields()
        }

        binding.createStepsBudgetItemsRecyclerView.apply {
            adapter = budgetsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }


        binding.createStepSaveStepButton.setOnClickListener {
            createStep(
                binding.createStepStepNameEditText.text.toString(),
                binding.createStepsEditStepDescriptionEditText.text.toString(),
                binding.createStepStepDurationAutoCompleteTextView.text.toString()
            )
        }

        binding.createStepSaveBudgetTextView.setOnClickListener {
            addBudget()

        }
        setUpCreateStepObserver()
        observeBudgetList()
    }

    private fun createStep(stepName: String, stepDescription: String, stepDuration: String) {
        if (viewModel.validateCreateStepsFields(stepName, stepDescription, stepDuration, this)) {
            viewModel.createStep(
                CreateStepsRequest(
                    stepName,
                    stepDescription,
                    stepDuration,
                    getTargetFunds(),
                    planId,
                    preferences.getUserId()
                ),
                "Bearer ${preferences.getToken()}"
            )
        }
    }

    private fun getTargetFunds(): Int {
        val targetFunds =
            binding.createStepsTargetAmountFigureTextView.text.toString().trim().split(" ")[1]
        return targetFunds.toInt()
    }

    private fun showAddBudgetFields() {
        if (binding.createStepBudgetNameTextInputLayout.visibility == View.GONE) {
            with(binding) {
                createStepBudgetCostEditText.visibility = View.VISIBLE
                createStepBudgetCostTextInputLayout.visibility = View.VISIBLE
                createStepBudgetNameEditText.visibility = View.VISIBLE
                createStepBudgetNameTextInputLayout.visibility = View.VISIBLE
                createStepSaveBudgetTextView.visibility = View.VISIBLE
                createStepsEnterBudgetCostTextView.visibility = View.VISIBLE
                createStepsEnterBudgetNameTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun addBudget() {
        val budgetName = binding.createStepBudgetNameEditText.text.toString()
        val budgetCost = binding.createStepBudgetCostEditText.text.toString()
        viewModel.addToBudgetList(AddBudget(budgetName, budgetCost))
        viewModel.budgetListLiveData.value?.let { budgetsAdapter.submitList(it) }
        budgetsAdapter.notifyDataSetChanged()
        with(binding) {
            createStepBudgetCostEditText.apply {
                visibility = View.GONE
                text?.clear()
            }
            createStepBudgetCostTextInputLayout.visibility = View.GONE
            createStepBudgetNameEditText.apply {
                visibility = View.GONE
                    text?.clear()
            }
            createStepBudgetNameTextInputLayout.visibility = View.GONE
            createStepSaveBudgetTextView.visibility = View.GONE
            createStepsEnterBudgetCostTextView.visibility = View.GONE
            createStepsEnterBudgetNameTextView.visibility = View.GONE
        }
    }

    private fun setUpCreateStepObserver() {
        viewModel.createStepResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    pleaseWaitDialog.show()
                }
                is Resource.Success -> {
                    makeSnackBar(getString(R.string.Step_created_successfully), requireView())
                    createBudget(it.data.data!!.id)
                    pleaseWaitDialog.dismiss()
                    findNavController().navigate(CreateStepsFragmentDirections.actionCreateStepsFragmentToCreatePlanFragment(planId), customNavAnimation().build())
                }
                is Resource.Error -> {
                    makeSnackBar(getString(R.string.Unable_to_create_step), requireView())
                    pleaseWaitDialog.dismiss()
                }
            }
        }
    }

    private fun createBudget(stepId: String) {
        if (viewModel.budgetListLiveData.value != null) {
            for (budget in viewModel.budgetListLiveData.value!!) {
                viewModel.createBudget(
                    CreateBudgetRequest(
                        budget.budgetName,
                        budget.budgetName,
                        budget.budgetCost.toFloat(),
                        planId,
                        stepId
                    ), "Bearer ${preferences.getToken()}"
                )
            }
        }
    }

    private fun observeBudgetList() {
        viewModel.budgetListLiveData.observe(viewLifecycleOwner) {
            var budgetCost = 0
            for (budget in it) {
                budgetCost += budget.budgetCost.toInt()
            }
            binding.createStepsTargetAmountFigureTextView.text = "NGN $budgetCost"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun deleteBudget(budget: AddBudget) {
        for (budgetItem in viewModel.getBudgetList()) {
            if (budgetItem.budgetName == budget.budgetName && budgetItem.budgetCost == budget.budgetCost){
                viewModel.deleteFromBudgetList(budgetItem)
                viewModel.budgetListLiveData.value?.let {
                    budgetsAdapter.submitList(it)
                }
                budgetsAdapter.notifyDataSetChanged()
            }
        }
    }
}
