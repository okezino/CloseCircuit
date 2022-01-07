package com.example.closedcircuitapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.databinding.FragmentCreateBudgetBinding
import com.mynameismidori.currencypicker.CurrencyPicker

class CreateBudgetFragment : Fragment() {
    private var _binding: FragmentCreateBudgetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createBudgetCurrencyPickerLayout.setOnClickListener {
            val picker = CurrencyPicker()
            picker.setListener { s, s2, s3, i ->
                binding.createBudgetCurrencyPickerIconImageView.setImageResource(i)
                binding.createBudgetCurrencyPickerCodeTextView.text = s2
                picker.dismiss()
            }
            picker.show(requireActivity().supportFragmentManager, "CURRENCY_PICKER")
        }
        binding.createBudgetSaveAndContinueButton.setOnClickListener {
//            find
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}