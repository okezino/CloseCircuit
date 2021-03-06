package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.common.utils.*
import com.example.closedcircuitapplication.databinding.FragmentEditPlanBinding
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.UpdatePlanRequest
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels.PlanViewModel
import com.example.closedcircuitapplication.beneficiary.plan.utils.PlanConstants.BEARER
import com.example.closedcircuitapplication.beneficiary.plan.utils.PlanConstants.PLAN_UPDATE_SUCCESS
import com.example.closedcircuitapplication.beneficiary.plan.utils.PlanUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditPlanFragment : Fragment(R.layout.fragment_edit_plan) {

    private lateinit var binding: FragmentEditPlanBinding
    private val viewModel: PlanViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences
    private var pleaseWaitDialog: AlertDialog? = null
    private val args: EditPlanFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()

        val options = resources.getStringArray(R.array.select_plan_category)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, options)
        with(binding){
            dropdownMenuPlanCategory.setAdapter(arrayAdapter)
            dropdownMenuPlanCategory.setDropDownBackgroundDrawable(ResourcesCompat.getDrawable(resources, R.drawable.text_input_background_dropdown, null))
        }

        val lenderOption = resources.getStringArray(R.array.Choose_business_Sector)
        val arrayAdapterLender = ArrayAdapter(requireContext(), R.layout.dropdown_item, lenderOption)
        with(binding){
            dropdownMenuPlanSector.setAdapter(arrayAdapterLender)
            dropdownMenuPlanSector.setDropDownBackgroundDrawable(ResourcesCompat.getDrawable(resources, R.drawable.text_input_background_dropdown, null))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditPlanBinding.bind(view)

        with(binding){
            dropdownMenuPlanSector.setText(args.planSector)
            fragmentEditPlanBusinessTypeEt.setText(args.planName)
            fragmentEditPlanDescribePlanEt.setText(args.planDescription)
            dropdownMenuPlanCategory.setText(args.planCategory)
            fragmentEditPlanPlanDurationEt.setText(args.planDuration)
            fragmentLetsCreateYourPlanSellingPriceEt.setText(args.planSellingPrice)
            fragmentEditPlanCostPriceEt.setText(args.planCostPrice)
        }
        Glide.with(this)
            .load(args.planAvatar)
            .into(binding.uploadImageHolderIv)

        updatePlanInitObserver()

        binding.fragmentEditPlanDescribePlanEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val descriptionText: Int = PlanUtils.planDescriptionMaxWords(binding.fragmentEditPlanDescribePlanEt.text.toString())
                if (descriptionText >= 201) {
                    binding.fragmentPlanSummary200MaxWordsTv.visibility = View.GONE
                    binding.fragmentPlanSummaryExceeded200MaxWordsTv.visibility = View.VISIBLE
                } else {
                    binding.fragmentPlanSummary200MaxWordsTv.setTextColor(Color.parseColor("#C4C4C4"))
                    binding.fragmentPlanSummary200MaxWordsTv.visibility = View.VISIBLE
                    binding.fragmentPlanSummaryExceeded200MaxWordsTv.visibility = View.GONE
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        val planAvatar = PlanUtils.avatarUpload(args.planAvatar)

        binding.fragmentEditPlanUpdatePlanBtn.setOnClickListener {

            if (!Validation.validateSellingPrice(binding.fragmentLetsCreateYourPlanSellingPriceEt.text.toString())) {
                binding.fragmentEditPlanEmptySellingPriceTv.visibility = View.VISIBLE
                binding.fragmentLetsCreateYourPlanSellingPriceEt.isFocusable = true
                return@setOnClickListener
            } else {
                binding.fragmentEditPlanEmptySellingPriceTv.visibility = View.GONE
            }

            if (!Validation.validatePlanName(capitalizeWords(binding.fragmentEditPlanBusinessTypeEt.text.toString()))) {
                binding.planNameFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.fragmentEditPlanBusinessTypeEt.isFocusable = true
                return@setOnClickListener
            } else {
                binding.planNameFieldCannotBeEmpty.visibility = View.GONE
            }

            if (!Validation.validatePlanDuration(binding.fragmentEditPlanPlanDurationEt.text.toString())) {
                binding.fragmentEditPlanEmptyPlanDurationTv.visibility = View.VISIBLE
                binding.fragmentEditPlanPlanDurationEt.isFocusable = true
                return@setOnClickListener
            } else {
                binding.fragmentEditPlanEmptyPlanDurationTv.visibility = View.GONE
            }

            if (!Validation.validateDescription(binding.fragmentEditPlanDescribePlanEt.text.toString())) {
                binding.describePlanFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.fragmentEditPlanDescribePlanEt.isFocusable = true
                return@setOnClickListener
            } else {
                binding.describePlanFieldCannotBeEmpty.visibility = View.GONE
            }
            if (!Validation.validateCostPrice(binding.fragmentEditPlanCostPriceEt.text.toString())){
                binding.fragmentEditPlanEmptyCostPriceTv.visibility = View.VISIBLE
                binding.fragmentEditPlanCostPriceEt.isFocusable = true
                return@setOnClickListener
            }else{
                binding.fragmentEditPlanEmptyCostPriceTv.visibility = View.GONE
            }
            viewModel.updateUserPlan(UpdatePlanRequest(
                planAvatar,
                capitalizeWords(binding.fragmentEditPlanBusinessTypeEt.text.toString()),
                binding.fragmentEditPlanCostPriceEt.text.toString(),
                binding.fragmentLetsCreateYourPlanSellingPriceEt.text.toString(),
                binding.dropdownMenuPlanCategory.text.toString(),
                binding.fragmentEditPlanDescribePlanEt.text.toString(),
                binding.fragmentEditPlanPlanDurationEt.text.toString(),
                binding.dropdownMenuPlanSector.text.toString(),
                "Physical Product"),
                args.planId
            )
        }
    }

    private fun updatePlanInitObserver(){
        viewModel.updatePlanResponse.observe(viewLifecycleOwner){resource ->
            when(resource){
                is Resource.Loading -> {
                    pleaseWaitDialog = showPleaseWaitAlertDialog()
                }
                is Resource.Success -> {
                    pleaseWaitDialog?.dismiss()
                    makeSnackBar(PLAN_UPDATE_SUCCESS, requireView())
                    findNavController().navigate(EditPlanFragmentDirections.actionEditPlanFragmentToCreatePlanFragment(args.planId), customNavAnimation().build())
                }
                is Resource.Error -> {
                    pleaseWaitDialog?.dismiss()
                    makeSnackBar("${resource.data?.message}", requireView())
                }
            }
        }
    }
}
