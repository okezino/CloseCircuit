package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanStep2Binding
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.PlanViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreatePlanStep2Fragment : Fragment() {
    @Inject
    lateinit var preferences: Preferences
    private var _binding: FragmentCreatePlanStep2Binding? = null
    private val binding get() = _binding!!
    private val args: CreatePlanStep2FragmentArgs by navArgs()
    private lateinit var businessNameEditText: EditText
    private lateinit var planDescriptionEditText: EditText
    private lateinit var planDuration: EditText
    private lateinit var estimatedSellingPrice: EditText
    private lateinit var estimatedCostPrice: EditText
    private val viewModel: PlanViewModel by viewModels()
    private lateinit var planAvatar: String
    private lateinit var planCategory: String
    private lateinit var planSector: String
    private lateinit var businessType: String
    private var pleaseWaitDialog: AlertDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreatePlanStep2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        businessNameEditText = binding.fragmentLetsCreateYourPlanBusinessNameEt
        planDescriptionEditText = binding.fragmentLetsCreateYourPlanDescribeYourPlanEt
        planDuration = binding.fragmentLetsCreateYourPlanMonthDurationEt
        estimatedSellingPrice = binding.fragmentLetsCreateYourPlanSellingPriceEt
        estimatedCostPrice = binding.fragmentLetsCreateYourPlanCostPriceEt
        planAvatar = if (args.planAvatar != null) args.planAvatar!! else ""
        planCategory = args.planCategory
        planSector = args.planSector
        businessType = args.businessType!!


        binding.fragmentLetsCreateYourPlanCreatePlanBtn.setOnClickListener {
            if (validateCreatePlanFields(
                    businessNameEditText,
                    planDescriptionEditText,
                    planDuration,
                    estimatedSellingPrice,
                    estimatedCostPrice
                )
            ) {
                viewModel.createPlan(
                    CreatePlanRequest(
                        planAvatar,
                        planCategory,
                        planSector,
                        businessType,
                        businessNameEditText.text.toString(),
                        planDescriptionEditText.text.toString(),
                        planDuration.text.toString(),
                        estimatedSellingPrice.text.toString(),
                        estimatedCostPrice.text.toString()
                    ),
                    "Bearer ${preferences.getToken()}"
                )
            }
        }
        setUpObserver()
    }

    private fun validateCreatePlanFields(
        businessName: EditText,
        planDescription: EditText,
        planDuration: EditText,
        sellingPrice: EditText,
        costPrice: EditText
    ): Boolean {
        if (businessName.text.toString().isEmpty()) {
            makeSnackBar("Business name must not be empty", requireView())
            return false
        }

        if (planDescription.text.toString().isEmpty()) {
            makeSnackBar("Plan description must not be empty", requireView())
            return false
        }

        if (planDuration.text.toString().isEmpty()) {
            makeSnackBar("Plan duration must not be empty", requireView())
            return false
        }

        if (sellingPrice.text.toString().isEmpty()) {
            makeSnackBar("Estimated selling price must not be empty", requireView())
            return false
        }

        if (costPrice.text.toString().isEmpty()) {
            makeSnackBar("Estimated cost price must not be empty", requireView())
            return false
        }
        return true
    }

    private fun setUpObserver() {
        viewModel.createPlanResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showPleaseWaitAlertDialog()
                }

                is Resource.Success -> {
                    findNavController().navigate(
                        CreatePlanStep2FragmentDirections.actionCreatePlanStep2FragmentToPlanCreatedSuccessfullyFragment(),
                        customNavAnimation().build()
                    )
                    showPleaseWaitAlertDialog().dismiss()
                }

                is Resource.Error -> {
                    showPleaseWaitAlertDialog().dismiss()
                    makeSnackBar("${it.data?.message}", requireView())
                }
            }
        }
    }

    private fun showPleaseWaitAlertDialog(): AlertDialog {
        if(pleaseWaitDialog == null) {
            pleaseWaitDialog = showCustomViewDialog(
                requireContext(), resources,
                R.layout.custom_login_wait_dialog,
                false
            )
        }
        return pleaseWaitDialog as AlertDialog
    }

}