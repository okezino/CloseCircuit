package com.example.closedcircuitapplication.projectSummary

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.Validation
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanSummaryBinding

class CreatePlanSummaryFragment : Fragment(R.layout.fragment_create_plan_summary) {

    private lateinit var binding: FragmentCreatePlanSummaryBinding
    private lateinit var description: String

    private val args: CreatePlanSummaryFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()

        // select your preferred means of support
        val options = resources.getStringArray(R.array.support_means)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, options)
        binding.dropdownMenuSupport.setAdapter(arrayAdapter)
        binding.dropdownMenuSupport.setDropDownBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.text_input_background_dropdown,
                null
            )
        )

        // select maximum number of lenders
        val lenderOption = resources.getStringArray(R.array.number_of_lenders)
        val arrayAdapterLender =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, lenderOption)
        binding.dropdownMenuLenders.setAdapter(arrayAdapterLender)
        binding.dropdownMenuLenders.setDropDownBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.text_input_background_dropdown,
                null
            )
        )

        // To mark checked when an item is selected
        binding.dropdownMenuSupport.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selectedItem = options[position].toString()
                if (selectedItem != "") {
                    val icon: Drawable =
                        binding.dropdownMenuSupport.context.resources.getDrawable(R.drawable.ic_done)
                    binding.dropdownMenuSupport.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        icon,
                        null
                    )
                }
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreatePlanSummaryBinding.bind(view)
        val createPlanButton = binding.fragmentSummaryCreatePlanBtn

        val categoryArgs = args.category
        val sectorArgs = args.sector
        val uriArgs = args.uri
        val myUri = Uri.parse(uriArgs)
        Log.d(
            "CREATE_PLAN_SUMMARY",
            "SECTOR=====> $sectorArgs  CATEGORY====> $categoryArgs URI====> $myUri "
        )
        binding.dropdownMenuCategory.setText(categoryArgs)
        binding.dropdownMenuSector.setText(sectorArgs)
        binding.uploadImageHolderIv.setImageURI(myUri)

        // To set the maximum number of characters to be entered when a user types in the description box.
        binding.fragmentSummaryDescribePlanEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                description = binding.fragmentSummaryDescribePlanEt.text.toString()
                description = description.replace("\n", " ")
                val descriptionText: List<String> = description.split(" ")
                if(descriptionText.size >= 201){
                    binding.fragmentPlanSummary200MaxWordsTv.visibility = View.GONE
                    binding.fragmentPlanSummaryExceeded200MaxWordsTv.visibility = View.VISIBLE

                }else{
//                    binding.fragmentPlanSummary200MaxWordsTv.setTextColor(Color.parseColor("#C4C4C4"))
                    binding.fragmentPlanSummary200MaxWordsTv.visibility = View.VISIBLE
                    binding.fragmentPlanSummaryExceeded200MaxWordsTv.visibility = View.GONE
                }

            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        createPlanButton.setOnClickListener {
            val businessType = binding.fragmentSummaryBusinessType.text.toString()
            val businessName = binding.fragmentSummaryEnterBusinessNameEt.text.toString()
            val planDescription = binding.fragmentSummaryDescribePlanEt.text.toString()
            val planDuration = binding.fragmentSummaryMonthDuration.text.toString()
            val support = binding.dropdownMenuSupport.text.toString()
            val minimum = binding.fragmentSummaryMinimumEt.text.toString()
            val maximum = binding.fragmentSummaryMaximumEt.text.toString()
            val lenders = binding.dropdownMenuLenders.text.toString()

            if (!Validation.validateBusinessType(businessType)){
                binding.businessTypeFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.businessTypeFieldCannotBeEmpty.isFocusable
                return@setOnClickListener
            }else{
                binding.businessTypeFieldCannotBeEmpty.visibility = View.GONE
                binding.businessTypeFieldCannotBeEmpty.isFocusable = false
            }

            if (!Validation.validateBusinessName(businessName)){
                binding.businessNameFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.businessTypeFieldCannotBeEmpty.isFocusable
                return@setOnClickListener
            }else{
                binding.businessNameFieldCannotBeEmpty.visibility = View.GONE
                binding.businessTypeFieldCannotBeEmpty.isFocusable = false
            }

            if (!Validation.validatePlanDuration(planDuration)){
                binding.planDurationFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.planDurationFieldCannotBeEmpty.isFocusable
                return@setOnClickListener
            }else{
                binding.planDurationFieldCannotBeEmpty.visibility = View.GONE
                binding.planDurationFieldCannotBeEmpty.isFocusable = false
            }

            if (!Validation.validateMinimumLoan(minimum)){
                binding.minimumFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.minimumFieldCannotBeEmpty.isFocusable
                return@setOnClickListener
            }else{
                binding.minimumFieldCannotBeEmpty.visibility = View.GONE
                binding.minimumFieldCannotBeEmpty.isFocusable = false
            }

            if (!Validation.validateMaximumLoan(maximum)){
                binding.maximumFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.maximumFieldCannotBeEmpty.isFocusable
                return@setOnClickListener
            }else{
                binding.maximumFieldCannotBeEmpty.visibility = View.GONE
                binding.maximumFieldCannotBeEmpty.isFocusable = false
            }

            if (!Validation.validateNumberOfLenders(lenders)){
                binding.lendersFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.lendersFieldCannotBeEmpty.isFocusable
                return@setOnClickListener
            }else{
                binding.describePlanFieldCannotBeEmpty.visibility = View.GONE
                binding.lendersFieldCannotBeEmpty.isFocusable = false
            }

            if (!Validation.validateDescription(planDescription)){
                binding.describePlanFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.describePlanFieldCannotBeEmpty.isFocusable
                return@setOnClickListener
            }else{
                binding.supportFieldCannotBeEmpty.visibility = View.GONE
                binding.describePlanFieldCannotBeEmpty.isFocusable = false
            }

            if (!Validation.validateSupport(support)){
                binding.supportFieldCannotBeEmpty.visibility = View.VISIBLE
                binding.supportFieldCannotBeEmpty.isFocusable
                return@setOnClickListener
            }else{
                binding.supportFieldCannotBeEmpty.visibility = View.GONE
                binding.supportFieldCannotBeEmpty.isFocusable = false
            }
            findNavController().navigate(R.id.createPlanFragment)
        }
    }

}
