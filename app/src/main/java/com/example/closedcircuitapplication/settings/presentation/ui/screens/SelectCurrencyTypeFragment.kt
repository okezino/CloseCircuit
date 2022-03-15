package com.example.closedcircuitapplication.settings.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentChangeCurrencyTypeBinding


class SelectCurrencyTypeFragment : Fragment() {
    private var _binding: FragmentChangeCurrencyTypeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeCurrencyTypeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencyType = resources.getStringArray(R.array.currency_type)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, currencyType)
        binding.fragmentSettingsCurrencyTypeDropdown.setAdapter(arrayAdapter)
        binding.fragmentSettingsCurrencyTypeDropdown.setDropDownBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.text_input_background_dropdown,
                null
            )
        )
    }
}