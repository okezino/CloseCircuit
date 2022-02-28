package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.USER_PHONE_NUMBER
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.example.closedcircuitapplication.common.utils.*
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
    private val viewModel: PlanViewModel by viewModels()
    private lateinit var planAvatar: String
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
        val phoneNumber = preferences.getUserPhoneNumber(USER_PHONE_NUMBER)
        if (phoneNumber.startsWith(getString(R.string.nigerian_country_code))){
            binding.apply {
                fragmentSummaryMaximumTv.text = getString(R.string.nigerian_currency_code)
                frgamentSummaryMinimumTv.text = getString(R.string.nigerian_currency_code)
            }
        }
        else if (phoneNumber.startsWith(getString(R.string.south_african_country_code))){
            binding.apply {
                fragmentSummaryMaximumTv.text = getString(R.string.south_african_currency_code)
                frgamentSummaryMinimumTv.text = getString(R.string.south_african_currency_code)
            }
        }
        planAvatar = if (args.planAvatar != null) args.planAvatar!! else ""

        binding.fragmentLetsCreateYourPlanCreatePlanBtn.setOnClickListener {
            with(binding) {
                if (validateCreatePlanFields(
                        fragmentLetsCreateYourPlanBusinessNameEt,
                        fragmentLetsCreateYourPlanDescribeYourPlanEt,
                        fragmentLetsCreateYourPlanMonthDurationEt,
                        fragmentLetsCreateYourPlanSellingPriceEt,
                        fragmentLetsCreateYourPlanCostPriceEt,
                        this@CreatePlanStep2Fragment
                    )
                ) {
                    viewModel.createPlan(
                        CreatePlanRequest(
                            planAvatar,
                            args.planCategory,
                            args.planSector,
                            args.businessType,
                            capitalizeWords(fragmentLetsCreateYourPlanBusinessNameEt.text.toString()),
                            fragmentLetsCreateYourPlanDescribeYourPlanEt.text.toString(),
                            fragmentLetsCreateYourPlanMonthDurationEt.text.toString(),
                            fragmentLetsCreateYourPlanSellingPriceEt.text.toString(),
                            fragmentLetsCreateYourPlanCostPriceEt.text.toString()
                        ),
                        "Bearer ${preferences.getToken()}"
                    )
                }
            }
        }
        setUpObserver()
    }

    //Function to observe create plan response
    private fun setUpObserver() {
        viewModel.createPlanResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showPleaseWaitAlertDialog()
                }

                is Resource.Success -> {
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
    //Function to create single instance of AlertDialog
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