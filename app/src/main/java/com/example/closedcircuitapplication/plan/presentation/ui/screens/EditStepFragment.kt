package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.network.models.Budget
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.common.utils.showPleaseWaitAlertDialog
import com.example.closedcircuitapplication.databinding.FragmentEditStepBinding
import com.example.closedcircuitapplication.plan.domain.models.CreateBudgetRequest
import com.example.closedcircuitapplication.plan.domain.models.UpdateBudgetRequest
import com.example.closedcircuitapplication.plan.domain.models.UpdateStepRequest
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.BudgetItemClickListeners
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.EditBudgetAdapter
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.AllStepsViewModel
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.StepsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditStepFragment : Fragment(), BudgetItemClickListeners {
    private var _binding: FragmentEditStepBinding? = null
    private val binding get() = _binding!!
    private val navArgs: EditStepFragmentArgs by navArgs()
    private val viewModel: StepsViewModel by viewModels()

    @Inject
    lateinit var preferences: Preferences
    private lateinit var pleaseWaitDialog: AlertDialog
    private lateinit var budgetsAdapter: EditBudgetAdapter
    private val allStepsViewModel: AllStepsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.edit_step)
        setUpEditableData()
        pleaseWaitDialog = showPleaseWaitAlertDialog()
        val weekAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.weeks,
            android.R.layout.simple_dropdown_item_1line
        )
        budgetsAdapter = EditBudgetAdapter(this)
        budgetsAdapter.submitList(getBudgetsForStep())
        binding.editStepStepDurationAutoCompleteTextView.setAdapter(weekAdapter)

        binding.editStepSaveStepButton.setOnClickListener {
            viewModel.updateStep(
                navArgs.step.id, "Bearer ${preferences.getToken()}", UpdateStepRequest(
                    binding.editStepStepNameEditText.text.toString(),
                    binding.editStepsEditStepDescriptionEditText.text.toString(),
                    binding.editStepStepDurationAutoCompleteTextView.text.toString(),
                    navArgs.step.target_funds.toFloat().toInt(),
                    navArgs.step.total_funds_raised.toFloat().toInt(),
                    navArgs.step.plan,
                    navArgs.step.user
                )
            )
        }

        binding.editStepSaveBudgetTextView.setOnClickListener {
            if (viewModel.editBudgetMode) performBudgetEdit() else performBudgetAddition()
            hideBudgetViews()
        }
        binding.editStepsAddBudgetTextView.setOnClickListener {
            viewModel.budgetBeingEdited?.let { it1 -> showBudgetViews(it1, false) }
        }

        setUpEditStepObservers()
        setUpRecyclerView()
        setUpEditBudgetObserver()
        setUpAddBudgetObserver()
        setUpGetAllBudgetsObserver()
    }

    private fun performBudgetAddition() {
        with(binding) {
            val newBudget = CreateBudgetRequest(
                editStepBudgetNameEditText.text.toString(),
                editStepBudgetNameEditText.text.toString(),
                editStepBudgetCostEditText.text.toString().toFloat(),
                navArgs.step.plan,
                navArgs.step.id
            )
            viewModel.newBudgetItem = newBudget
            viewModel.createBudget(newBudget, "Bearer ${preferences.getToken()}")
        }
    }

    private fun setUpAddBudgetObserver() {
        viewModel.createBudgetResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    allStepsViewModel.getUserBudgets("Bearer ${preferences.getToken()}")
                }
                else -> {
                    pleaseWaitDialog.dismiss()
                }
            }
        }
    }

    private fun setUpGetAllBudgetsObserver() {
        allStepsViewModel.getBudgetResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val budgetsForStep = arrayListOf<Budget>()
                    it.data.data?.budgets?.forEach { budget ->
                        if (budget.step == navArgs.step.id) {
                            budgetsForStep.add(budget)
                        }
                    }
                    budgetsAdapter.submitList(budgetsForStep)
                    budgetsAdapter.notifyDataSetChanged()
                }
                else -> {
                    pleaseWaitDialog.dismiss()
                }
            }
        }
    }

    private fun performBudgetEdit() {
        with(binding) {
            viewModel.budgetBeingEdited?.let {
                viewModel.updateBudget(
                    it.id, "Bearer ${preferences.getToken()}", UpdateBudgetRequest(
                        editStepBudgetNameEditText.text.toString(),
                        editStepBudgetNameEditText.text.toString(),
                        editStepBudgetCostEditText.text.toString().toFloat(),
                        navArgs.step.plan,
                        navArgs.step.id
                    )
                )
            }
            viewModel.budgetBeingEdited?.apply {
                budget_name = editStepBudgetNameEditText.text.toString()
                budget_cost = editStepBudgetCostEditText.text.toString().toFloat()
            }
        }

    }

    private fun setUpEditBudgetObserver() {
        viewModel.updateBudgetResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    viewModel.budgetBeingEdited?.let { it1 ->
                        budgetsAdapter.updateBudgetItem(
                            it1,
                            viewModel.positionOfBudgetBeingEdited
                        )
                    }
                    pleaseWaitDialog.dismiss()
                }
                is Resource.Loading -> {
                    pleaseWaitDialog.show()
                }
                is Resource.Error -> {
                    pleaseWaitDialog.dismiss()
                    makeSnackBar(getString(R.string.unable_to_update_budget), requireView())
                }
            }
        }
    }

    private fun setUpEditStepObservers() {
        viewModel.updateStepsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    makeSnackBar(getString(R.string.successfully_edited), requireView())
                    pleaseWaitDialog.dismiss()
                    findNavController().navigate(EditStepFragmentDirections.actionEditStepFragmentToCreatePlanFragment())
                }
                is Resource.Loading -> {
                    pleaseWaitDialog.show()
                }
                is Resource.Error -> {
                    pleaseWaitDialog.dismiss()
                }
            }
        }
    }

    private fun setUpEditableData() {
        binding.editStepStepNameEditText.setText(navArgs.step.step_name)
        binding.editStepsEditStepDescriptionEditText.setText(navArgs.step.step_description)
        binding.editStepStepDurationAutoCompleteTextView.setText(navArgs.step.duration)
    }

    private fun setUpRecyclerView() {
        binding.editStepsBudgetItemsRecyclerView.apply {
            adapter = budgetsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getBudgetsForStep(): ArrayList<Budget> {
        val budgetsForStep: ArrayList<Budget> = arrayListOf()
        for (budget in allStepsViewModel.budgetsForPlan) {
            if (budget.step == navArgs.step.id) {
                budgetsForStep.add(budget)
            }
        }
        return budgetsForStep
    }

    private fun hideBudgetViews() {
        with(binding) {
            editStepBudgetNameEditText.apply {
                visibility = View.GONE
                text?.clear()
            }
            editStepBudgetNameTextInputLayout.visibility = View.GONE
            editStepBudgetCostEditText.apply {
                visibility = View.GONE
                text?.clear()
            }
            editStepBudgetCostTextInputLayout.visibility = View.GONE
            editStepSaveBudgetTextView.visibility = View.GONE
            viewModel.editBudgetMode = false
        }
    }

    private fun showBudgetViews(budget: Budget, editMode: Boolean) {
        with(binding) {
            editStepBudgetNameEditText.apply {
                visibility = View.VISIBLE
                if (editMode) setText(budget.budget_name)
            }
            editStepBudgetNameTextInputLayout.visibility = View.VISIBLE
            editStepBudgetCostEditText.apply {
                visibility = View.VISIBLE
                if (editMode) setText(budget.budget_cost.toString())
            }
            editStepBudgetCostTextInputLayout.visibility = View.VISIBLE
            editStepSaveBudgetTextView.visibility = View.VISIBLE
            viewModel.editBudgetMode = editMode
        }
    }


    override fun deleteBudget(budgetItem: Budget, position: Int) {
        val deleteBudgetDialog = AlertDialog.Builder(requireContext())
        deleteBudgetDialog.setMessage(getString(R.string.do_you_want_to_delete_budget) + budgetItem.budget_name + "?")
        deleteBudgetDialog.setPositiveButton(
            getString(R.string.yes)
        ) { dialog, which ->
            viewModel.deleteBudget(budgetItem.id, "Bearer ${preferences.getToken()}")
            budgetsAdapter.deleteBudgetItem(budgetItem, position)
            dialog.dismiss()
        }
        deleteBudgetDialog.setNegativeButton(getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }
        deleteBudgetDialog.create().show()
    }


    override fun editBudget(budget: Budget, position: Int) {
        showBudgetViews(budget, true)
        viewModel.budgetBeingEdited = budget
        viewModel.positionOfBudgetBeingEdited = position
    }
}