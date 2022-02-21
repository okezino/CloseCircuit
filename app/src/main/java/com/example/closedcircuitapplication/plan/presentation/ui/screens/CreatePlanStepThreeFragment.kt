package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanStepThreeBinding


class CreatePlanStepThreeFragment : Fragment() {

    private var _binding: FragmentCreatePlanStepThreeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreatePlanStepThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        enableCreatePlanButton()
    }

    private fun setUpUi() {
        val meansOfSupportArray = resources.getStringArray(R.array.means_of_support)
        val meansOfSupportAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, meansOfSupportArray)
        binding.createPlanMeansOfSupportAutocompleteTextView.apply {
            setAdapter(meansOfSupportAdapter)
            binding.createPlanStepThreeCreatePlanButton.setOnClickListener {
//                val action =
//                    CreatePlanStepThreeFragmentDirections.actionCreatePlanStepThreeFragmentToCreatePlanFragment()
//                findNavController().navigate(action, customNavAnimation().build())
            }
        }

    }

    private fun enableCreatePlanButton() {
        binding.createPlanMeansOfSupportAutocompleteTextView.setOnItemClickListener { parent, view, position, id ->
            binding.createPlanStepThreeCreatePlanButton.apply {
                isEnabled = true
                setBackgroundColor(resources.getColor(R.color.closed_circuit_dark_green))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}