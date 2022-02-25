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
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.USER_PHONE_NUMBER
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.capitalizeWords
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
    private  var businessType: String? = null
    private var pleaseWaitDialog: AlertDialog? = null
    private lateinit var planId: String


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
        val phoneNumber = preferences.getUserPhoneNumber(USER_PHONE_NUMBER)
        if (phoneNumber.startsWith("+234")){
            binding.apply {
                fragmentSummaryMaximumTv.text = "NGN"
                frgamentSummaryMinimumTv.text = "NGN"
            }
        }
        else if (phoneNumber.startsWith("+27")){
            binding.apply {
                fragmentSummaryMaximumTv.text = "ZAR"
                frgamentSummaryMinimumTv.text = "ZAR"
            }
        }
        businessNameEditText = binding.fragmentLetsCreateYourPlanBusinessNameEt
        planDescriptionEditText = binding.fragmentLetsCreateYourPlanDescribeYourPlanEt
        planDuration = binding.fragmentLetsCreateYourPlanMonthDurationEt
        estimatedSellingPrice = binding.fragmentLetsCreateYourPlanSellingPriceEt
        estimatedCostPrice = binding.fragmentLetsCreateYourPlanCostPriceEt
        planAvatar = if (args.planAvatar != null) args.planAvatar!! else ""
        planCategory = args.planCategory
        planSector = args.planSector
        businessType = args.businessType


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
                        capitalizeWords(businessNameEditText.text.toString()),
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
            makeSnackBar(getString(R.string.Plan_name_must_not_be_empty_text), requireView())
            return false
        }

        if (planDescription.text.toString().isEmpty()) {
            makeSnackBar(getString(R.string.Plan_description_must_not_be_empty_text), requireView())
            return false
        }

        if (planDuration.text.toString().isEmpty()) {
            makeSnackBar(getString(R.string.Plan_duraton_must_not_be_empty_text), requireView())
            return false
        }

        if (sellingPrice.text.toString().isEmpty()) {
            makeSnackBar(getString(R.string.Estimated_selling_price_must_not_be_empty_text), requireView())
            return false
        }

        if (costPrice.text.toString().isEmpty()) {
            makeSnackBar(getString(R.string.Estimated_cost_must_not_be_empty_text), requireView())
            return false
        }

        if (costPrice.text.toString().toFloat() > sellingPrice.text.toString().toFloat()) {
            makeSnackBar(getString(R.string.Cost_price_cannot_be_greater_text),requireView())
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
//                    planId = it.data.data?.id.toString()
                    val planId = it.datas?.data!!.id
                    findNavController().navigate(
                        CreatePlanStep2FragmentDirections.actionCreatePlanStep2FragmentToPlanCreatedSuccessfullyFragment(planId),
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