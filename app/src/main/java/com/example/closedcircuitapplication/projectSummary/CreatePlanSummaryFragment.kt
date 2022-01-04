package com.example.closedcircuitapplication.projectSummary

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
        createPlanButton.setOnClickListener {
            findNavController().navigate(R.id.createPlanFragment)
        }
    }

}
