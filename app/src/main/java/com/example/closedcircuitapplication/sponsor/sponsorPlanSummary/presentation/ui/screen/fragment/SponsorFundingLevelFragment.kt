package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.ui.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentSponsorFundingLevelBinding

class SponsorFundingLevelFragment : Fragment() {

    private var _binding: FragmentSponsorFundingLevelBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSponsorFundingLevelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fundLevelTypeAdapter()
    }
    private fun fundLevelTypeAdapter(){
        val fundLevelType = resources.getStringArray(R.array.plan_fund_level)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.sponsor_funding_level_layout,R.id.sponsor_fund_level_layout_tv,fundLevelType )
        with(binding){
            fragmentSponsorSelectFundLevelDropdown.setAdapter(arrayAdapter)
            fragmentSponsorSelectFundLevelDropdown.setDropDownBackgroundDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.text_input_background_dropdown,
                    null
                )
            )
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}