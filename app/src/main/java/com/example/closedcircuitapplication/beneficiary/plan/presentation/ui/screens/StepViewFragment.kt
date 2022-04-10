package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.data.network.models.Budget
import com.example.closedcircuitapplication.common.common.data.network.models.Step
import com.example.closedcircuitapplication.common.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.DeleteStepDialogBinding
import com.example.closedcircuitapplication.databinding.FragmentStepViewBinding
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.adapters.BudgetsAdapter
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels.AllStepsViewModel
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels.StepsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StepViewFragment : Fragment() {
    private lateinit var budgetData: Step
    private var _binding: FragmentStepViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var budgetItemAdapter: BudgetsAdapter
    private val activityViewModel: AllStepsViewModel by activityViewModels()
    private val stepsViewModel: StepsViewModel by viewModels()

    @Inject
    lateinit var preferences: Preferences
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
        budgetData = arguments?.getParcelable(getString(R.string.budget_data))!!
        binding.myStepTargetAmountValueTextView.text = budgetData.target_funds
        binding.myStepsFundsRaisedValueTextView.text = budgetData.total_funds_raised
        binding.myStepStepNameTextView.text = budgetData.step_name
        binding.myStepStepDescriptionDescriptionTextView.text  = budgetData.step_description
        budgetItemAdapter = BudgetsAdapter()
        budgetItemAdapter.submitList(getBudgetsForStep())
        binding.myStepBudgetsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = budgetItemAdapter
        }
        setUpPopUpMenu()
    }

    private fun getBudgetsForStep(): ArrayList<Budget> {
        val budgetForStep = arrayListOf<Budget>()
        for (budget in activityViewModel.budgetsForPlan) {
            if (budget.step == budgetData.id) {
                budgetForStep.add(budget)
            }
        }
        return budgetForStep
    }

    private fun setUpPopUpMenu() {
        binding.myStepMoreOptionsImageView.setOnClickListener {
            val popUpMenu = PopupMenu(requireContext(), it)
            popUpMenu.inflate(R.menu.step_menu)
            popUpMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.step_edit -> {
                        findNavController().navigate(
                            StepViewFragmentDirections.actionStepViewFragmentToEditStepFragment(budgetData),
                            customNavAnimation().build()
                        )
                        return@setOnMenuItemClickListener true
                    }
                    R.id.step_delete -> {
                        setUpDeleteStepDialog()
                        return@setOnMenuItemClickListener true
                    }
                    else -> return@setOnMenuItemClickListener false
                }
            }
            try {
                val fieldMPopup =
                    androidx.appcompat.widget.PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popUpMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
            } finally {
                popUpMenu.show()
            }
        }
    }

    private fun setUpDeleteStepDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val deleteStepDialogBinding = DeleteStepDialogBinding.inflate(layoutInflater)
        dialog.setView(deleteStepDialogBinding.root)
        deleteStepDialogBinding.deleteStepDialogPositiveActionTextView.setOnClickListener {
            stepsViewModel.deleteStep(budgetData.id, "Bearer ${preferences.getToken()}")
            dialog.dismiss()
            findNavController().navigate(
                StepViewFragmentDirections.actionStepViewFragmentToCreatePlanFragment()
            )
        }
        deleteStepDialogBinding.deleteStepDialogNegativeActionTextView.setOnClickListener {
            dialog.dismiss()
        }
        deleteStepDialogBinding.deleteStepDialogCloseImageView.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}