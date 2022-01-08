package com.example.closedcircuitapplication.ui.currencyType

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentCurrencyTypeBinding


class CurrencyTypeFragment : Fragment(R.layout.fragment_currency_type) {
    private lateinit var binding: FragmentCurrencyTypeBinding
    private  var selected:String? = null
    private val args: CurrencyTypeFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        // select your preferred means of support
        val currencyType = resources.getStringArray(R.array.currency_type)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, currencyType)
        binding.dropdownMenuCurrencyType.setAdapter(arrayAdapter)
        binding.dropdownMenuCurrencyType.setDropDownBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.text_input_background_dropdown,
                null
            )
        )
        val categoryArgs = args.category
        val sectorArgs = args.sector
        val uriArgs = args.uri
        val currencyTypeArgs = args.currencyType

//        binding.dropdownMenuCurrencyType.setText(currencyTypeArgs)

        binding.dropdownMenuCurrencyType.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selectedCurrency = currencyType[position].toString()
//                selected = selectedCurrency
                val action = CurrencyTypeFragmentDirections.actionCurrencyTypeFragmentToCreatePlanSummaryFragment2(sectorArgs,categoryArgs,uriArgs,selectedCurrency)
                findNavController().navigate(action)
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrencyTypeBinding.bind(view)


    }
}