package com.example.closedcircuitapplication.plan.utils

import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanStep2Binding

fun Fragment.currencyChecker(phoneNumber: String, binding: FragmentCreatePlanStep2Binding){
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
}