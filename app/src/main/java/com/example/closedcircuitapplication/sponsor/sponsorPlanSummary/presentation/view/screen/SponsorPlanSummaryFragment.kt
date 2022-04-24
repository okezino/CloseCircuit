package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.view.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.common.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.common.utils.handleBackPress
import com.example.closedcircuitapplication.common.common.utils.popBackStack
import com.example.closedcircuitapplication.databinding.FragmentSponsorPlanSummaryBinding
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.model.SponsorPlanSummaryStepDto
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.view.adapter.adapter.PlanSummaryStepAdapter
import com.example.closedcircuitapplication.utils.sponsorPlanSummaryStepItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SponsorPlanSummaryFragment : Fragment() {
    private var _binding: FragmentSponsorPlanSummaryBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSponsorPlanSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleBackPress()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val itemList: ArrayList<SponsorPlanSummaryStepDto> = arrayListOf()
        itemList.clear()
        itemList.addAll(sponsorPlanSummaryStepItem)
        binding.fragmentSponsorPlanSummaryRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val recyclerView = PlanSummaryStepAdapter(itemList)
            adapter = recyclerView
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onResume() {
        super.onResume()
        preferences.setDeepLinkedState(false)
    }

}