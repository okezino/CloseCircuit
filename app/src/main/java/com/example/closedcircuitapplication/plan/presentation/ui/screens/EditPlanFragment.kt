package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Validation
import com.example.closedcircuitapplication.databinding.FragmentEditPlanBinding
import com.example.closedcircuitapplication.plan.domain.models.UpdatePlanRequest
import com.example.closedcircuitapplication.plan.presentation.viewModel.PlanViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditPlanFragment : Fragment(R.layout.fragment_edit_plan) {

    private lateinit var binding: FragmentEditPlanBinding
    private lateinit var description: String
    private val viewModel: PlanViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences

//    private val args: FragmentEditPlanBinding by navArgs()

    override fun onResume() {
        super.onResume()

        // select your preferred plan category
        val options = resources.getStringArray(R.array.select_plan_category)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, options)
        binding.dropdownMenuPlanCategory.setAdapter(arrayAdapter)
        binding.dropdownMenuPlanCategory.setDropDownBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.text_input_background_dropdown,
                null
            )
        )

        // select your preferred plan sector
        val lenderOption = resources.getStringArray(R.array.Choose_business_Sector)
        val arrayAdapterLender =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, lenderOption)
        binding.dropdownMenuPlanSector.setAdapter(arrayAdapterLender)
        binding.dropdownMenuPlanSector.setDropDownBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.text_input_background_dropdown,
                null
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditPlanBinding.bind(view)

        val planId = "97aa0fc8-95c6-4420-8db0-56b03bd22b0f"
        val token = preferences.getToken()
        updatePlanInitObserver()

        // To set the maximum number of characters to be entered when a user types in the description box.
        binding.fragmentEditPlanDescribePlanEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                description = binding.fragmentEditPlanDescribePlanEt.text.toString()
                description = description.replace("\n", " ")
                val descriptionText: List<String> = description.split(" ")
                if (descriptionText.size >= 201) {
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

        binding.fragmentEditPlanUpdatePlanBtn.setOnClickListener {

            val planSector = binding.dropdownMenuPlanSector.text.toString()
            val planName = binding.fragmentEditPlanBusinessTypeEt.text.toString()
            val planDescription = binding.fragmentEditPlanDescribePlanEt.text.toString()
            val planCategory = binding.dropdownMenuPlanCategory.text.toString()
            val planDuration = binding.fragmentEditPlanPlanDurationEt.text.toString()
            val sellingPrice = binding.fragmentLetsCreateYourPlanSellingPriceEt.text.toString()
            val costPrice = binding.fragmentEditPlanCostPriceEt.text.toString()

            if (!Validation.validateSellingPrice(sellingPrice)) {
                binding.fragmentEditPlanEmptySellingPriceTv.visibility = View.VISIBLE
                binding.fragmentLetsCreateYourPlanSellingPriceEt.isFocusable = true
                return@setOnClickListener
            } else {
                binding.fragmentEditPlanEmptySellingPriceTv.visibility = View.GONE
//                binding.fragmentLetsCreateYourPlanSellingPriceEt.isFocusable = false
            }

            if (!Validation.validatePlanName(planName)) {
                binding.planNameFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.fragmentEditPlanBusinessTypeEt.isFocusable = true
                return@setOnClickListener
            } else {
                binding.planNameFieldCannotBeEmpty.visibility = View.GONE
            }

            if (!Validation.validatePlanDuration(planDuration)) {
                binding.fragmentEditPlanEmptyPlanDurationTv.visibility = View.VISIBLE
                binding.fragmentEditPlanPlanDurationEt.isFocusable = true
                return@setOnClickListener
            } else {
                binding.fragmentEditPlanEmptyPlanDurationTv.visibility = View.GONE
            }

            if (!Validation.validateDescription(planDescription)) {
                binding.describePlanFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.fragmentEditPlanDescribePlanEt.isFocusable = true
                return@setOnClickListener
            } else {
                binding.describePlanFieldCannotBeEmpty.visibility = View.GONE
            }
            if (!Validation.validateCostPrice(costPrice)){
                binding.fragmentEditPlanEmptyCostPriceTv.visibility = View.VISIBLE
                binding.fragmentEditPlanCostPriceEt.isFocusable = true
                return@setOnClickListener
            }else{
                binding.fragmentEditPlanEmptyCostPriceTv.visibility = View.GONE
            }

            viewModel.updateUserPlan(UpdatePlanRequest(
                "emmi",planName,costPrice,sellingPrice,planCategory,planDescription,planDuration,planSector, "Physical Product"), planId, "Bearer $token")
        }
    }

    private fun updatePlanInitObserver(){
        viewModel.updatePlanResponse.observe(viewLifecycleOwner){resource ->
            when(resource){
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "${resource.data.data?.id}", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
