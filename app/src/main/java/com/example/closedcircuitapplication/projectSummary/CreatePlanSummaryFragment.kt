package com.example.closedcircuitapplication.projectSummary

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanSummaryBinding

class CreatePlanSummaryFragment : Fragment(R.layout.fragment_create_plan_summary) {

    private lateinit var binding: FragmentCreatePlanSummaryBinding

    override fun onResume() {
        super.onResume()

        // select your preferred means of support
        val options = resources.getStringArray(R.array.support_means)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, options)
        binding.dropdownMenuSupport.setAdapter(arrayAdapter)
        binding.dropdownMenuSupport.setDropDownBackgroundDrawable(ResourcesCompat.getDrawable(resources, R.drawable.text_input_background_dropdown, null))

        // select maximum number of lenders
        val lenderOption = resources.getStringArray(R.array.number_of_lenders)
        val arrayAdapterLender = ArrayAdapter(requireContext(), R.layout.dropdown_item, lenderOption)
        binding.dropdownMenuLenders.setAdapter(arrayAdapterLender)
        binding.dropdownMenuLenders.setDropDownBackgroundDrawable(ResourcesCompat.getDrawable(resources, R.drawable.text_input_background_dropdown, null))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreatePlanSummaryBinding.bind(view)
    }
}
