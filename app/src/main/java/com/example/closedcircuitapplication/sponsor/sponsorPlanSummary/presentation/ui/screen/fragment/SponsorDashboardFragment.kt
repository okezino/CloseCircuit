package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.ui.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentSponsorsDashboardBinding
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.data.PlansFunded
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.ui.PlansFundedAdapter
import com.example.closedcircuitapplication.utils.planFundedItem

class SponsorDashboardFragment : Fragment() {
    private  var _binding: FragmentSponsorsDashboardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSponsorsDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(planFundedItem)
    }

    private fun setUpRecyclerView(itemList : List<PlansFunded>){
        val plansFundedAdapter = PlansFundedAdapter(requireContext(),itemList)
        val  plansFundedRecyclerView= binding.sponsorPlansFundedRV
        if (itemList.isNotEmpty()){
            plansFundedRecyclerView.visibility = View.VISIBLE
            plansFundedRecyclerView.layoutManager = LinearLayoutManager(context)
            plansFundedRecyclerView.adapter = plansFundedAdapter
            hideNoPlanViews()
        }else {
            plansFundedRecyclerView.visibility = View.GONE
            binding.fundingRequestViewAllLayout.visibility = View.GONE
            binding.viewAllLayout.visibility=View.GONE
            binding.noPlanYetTv.text = getString(R.string.no_plan_yet, "Benjamin")
        }
    }

    private fun hideNoPlanViews(){
        binding.noPlanIconIV.visibility = View.GONE
        binding.noPlanBackground.visibility =View.GONE
        binding.noPlanTv.visibility = View.GONE
        binding.noPlanYetTv.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}