package com.example.closedcircuitapplication

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.compose.ui.res.fontResource
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.databinding.FragmentCreateStepsBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable


class CreateStepsFragment : Fragment() {
    private var _binding: FragmentCreateStepsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateStepsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var weekAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.weeks,
            android.R.layout.simple_dropdown_item_1line
        )
        binding.createStepAutoCompleteTextView.apply {
            setAdapter(weekAdapter)
        }
        binding.createStepsNotHaveABudgetTextView.setOnClickListener {
            findNavController().navigate(R.id.action_createStepsFragment_to_createBudgetFragment)
        }
        addChips()
    }

    private fun addChips() {
        val contextThemeWrapper = ContextThemeWrapper(context, R.style.Widget_App_Chip)
        val travellingChip = Chip(contextThemeWrapper)
        val engineeringChip = Chip(contextThemeWrapper)
        val chipDrawable = ChipDrawable.createFromAttributes(requireContext(), null, 0,R.style.Widget_App_Chip)
        travellingChip.apply {
            text = "Travelling"
            textSize = 15F
            setChipDrawable(chipDrawable)
            height = 70
            closeIcon =
                ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.ic_chip_close, null)
            setOnCloseIconClickListener {
                binding.createStepSelectBudgetForStepsTextInputLayout.removeView(travellingChip)
            }
        }
        engineeringChip.apply {
            setText("Engineering")
            isCheckable = false
            textSize = 15F
            setChipDrawable(chipDrawable)
            height = 70
            chipEndPadding = 10F
            closeIcon =
                ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.ic_chip_close, null)
            setOnCloseIconClickListener {
                binding.createStepSelectBudgetForStepsTextInputLayout.removeView(engineeringChip)
            }
            setLayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        }
        binding.createStepSelectBudgetForStepsTextInputLayout.apply {
            addView(travellingChip)
            addView(engineeringChip)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}