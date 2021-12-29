package com.example.closedcircuitapplication.projectSummary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanSummaryBinding


class CreatePlanSummaryFragment : Fragment(R.layout.fragment_create_plan_summary) {

    private lateinit var binding: FragmentCreatePlanSummaryBinding
    private lateinit var description: String

    override fun onResume() {
        super.onResume()

        //select your preferred means of support
        val options = resources.getStringArray(R.array.support_means)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,options)
        binding.dropdownMenuSupport.setAdapter(arrayAdapter)
        binding.dropdownMenuSupport.setDropDownBackgroundDrawable(ResourcesCompat.getDrawable(resources,R.drawable.text_input_background_dropdown,null))

        //select maximum number of lenders
        val lenderOption = resources.getStringArray(R.array.number_of_lenders)
        val arrayAdapterLender = ArrayAdapter(requireContext(),R.layout.dropdown_item,lenderOption)
        binding.dropdownMenuLenders.setAdapter(arrayAdapterLender)
        binding.dropdownMenuLenders.setDropDownBackgroundDrawable(ResourcesCompat.getDrawable(resources,R.drawable.text_input_background_dropdown,null))

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreatePlanSummaryBinding.bind(view)

//        val descriptionArray = arrayOf<String>()
//        binding.fragmentSummaryDescribePlanEt.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                description = binding.fragmentSummaryDescribePlanEt.text.toString()
//                description = description.replace("\n"," ")
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
//            }
//
//        })

    }
//    fun onPlanDescriptionInputChangeListener(description: String){
//        if (description.isEmpty()){
//            binding.fragmentSummaryDescribePlanEt.error = "This field cannot be empty"
//        }
//    }

}