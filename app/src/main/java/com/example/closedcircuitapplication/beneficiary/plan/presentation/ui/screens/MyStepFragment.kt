package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.app.Dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.common.utils.deletePlanAlertDialog
import com.example.closedcircuitapplication.databinding.DeletePlanDialogBinding
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.adapters.MyStepsBudgetAdapter
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.StepsBudgetItem
import com.example.closedcircuitapplication.databinding.FragmentMyStepBinding
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels.PlanViewModel
import com.example.closedcircuitapplication.utils.budgetList
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyStepFragment : Fragment() {
    private var _binding: FragmentMyStepBinding? = null
    private val binding get() = _binding!!
    private val budgetAdapter = MyStepsBudgetAdapter()
    private lateinit var budgetData: StepsBudgetItem
    private lateinit var  planId : String
    private lateinit var   deleteDialog : Dialog
    private val viewModel: PlanViewModel by viewModels()
//    private val args: MyStepFragmentArgs by navArgs()

    @Inject
    lateinit var preferences : Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        budgetData = arguments?.getParcelable("budgetData")!!

        initObservers()

        val dialogBinding = DeletePlanDialogBinding.inflate(layoutInflater)
//        if (args != null) {
//            planId = args.planId
//        }
        budgetAdapter.submitList(budgetList)
        manageUiView()
        binding.myStepsRecyclerView.apply {
            adapter = budgetAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        binding.myStepDeleteIconImageView.setOnClickListener {
            val planName = binding.myStepStepNameTextView
            deleteDialog = deletePlanAlertDialog(dialogBinding, planName){deletePlan()}
                deleteDialog.show()
        }

    }

    private fun deletePlan(){
        viewModel.deletePlan("$planId/")
    }
    private fun manageUiView() {
        binding.myStepTargetAmountValueTextView.text = budgetData.targetFunds
        binding.myStepsFundsRaisedValueTextView.text = budgetData.totalFundsRaised

        if (binding.myStepTargetAmountValueTextView.text == binding.myStepsFundsRaisedValueTextView.text) {
           binding.myStepStepFundedCheckMarkImageView.visibility = View.VISIBLE
           binding.myStepDeleteIconImageView.setImageResource(R.drawable.ic_blurred_trash)
           binding.myStepsEditIconImageView.setImageResource(R.drawable.ic_blurred_edit)
       }
    }

    private fun initObservers(){
        viewModel.deletePlanResponse.observe(viewLifecycleOwner){ resources ->
            when (resources){
                is Resource.Loading ->{
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success ->{
                    findNavController().navigate(MyStepFragmentDirections.actionMyStepFragmentToProjectScreenFragment(), customNavAnimation().build())
                    Toast.makeText(requireContext(), "Plan delete ${resources.data.message}full", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error ->{
                    Snackbar.make( binding.root, "${resources.data!!.errors}", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}