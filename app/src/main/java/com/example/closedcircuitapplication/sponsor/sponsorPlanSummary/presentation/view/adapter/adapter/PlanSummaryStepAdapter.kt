package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.view.adapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.SponsorStepRecyclerviewItemBinding
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.model.SponsorPlanSummaryStepDto
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.view.adapter.PlanSummaryBudgetAdapter

class PlanSummaryStepAdapter
    (
        private val planStepList: ArrayList<SponsorPlanSummaryStepDto>
    ) : RecyclerView.Adapter<PlanSummaryStepAdapter.SponsorPlanSummaryStepViewHolder>() {

    inner class SponsorPlanSummaryStepViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: SponsorStepRecyclerviewItemBinding = SponsorStepRecyclerviewItemBinding.bind(view)

        fun bindStepsViews(stepItems: SponsorPlanSummaryStepDto){
            binding.fragmentSponsorPlanSummaryStepTv.text = stepItems.step
            binding.fragmentSponsorPlanSummaryStepNameTv.text = stepItems.stepName
            binding.fragmentSponsorPlanSummaryStepAmountTv.text = "Cost(NGN ${stepItems.stepCost})"
            bindBudgetViews(stepItems)
        }
        private fun bindBudgetViews(stepItems: SponsorPlanSummaryStepDto){
            binding.sponsorStepRecyclerView.apply {
                val recyclerView = PlanSummaryBudgetAdapter()
                recyclerView.addBudgetList(stepItems.budget)
                adapter = recyclerView
                val stackedLayoutManager= LinearLayoutManager(context)
                layoutManager = stackedLayoutManager

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorPlanSummaryStepViewHolder {
        val stepView = LayoutInflater.from(parent.context).inflate(R.layout.sponsor_step_recyclerview_item,parent,false)
        return SponsorPlanSummaryStepViewHolder(stepView)
    }

    override fun onBindViewHolder(holder: SponsorPlanSummaryStepViewHolder, position: Int) {
        val stepListItems = planStepList[position]
        holder.bindStepsViews(stepListItems)
    }

    override fun getItemCount(): Int {
        return planStepList.size
    }

}