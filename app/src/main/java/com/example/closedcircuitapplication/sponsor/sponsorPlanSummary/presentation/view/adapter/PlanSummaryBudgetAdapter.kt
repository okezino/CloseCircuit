package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.SponsorBudgetRecyclerviewItemBinding
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.model.SponsorPlanSummaryBudgetDto

class PlanSummaryBudgetAdapter :RecyclerView.Adapter<PlanSummaryBudgetAdapter.SponsorPlanSummaryBudgetViewHolder>(){

    private val budgetList : ArrayList<SponsorPlanSummaryBudgetDto> = arrayListOf()
    inner class SponsorPlanSummaryBudgetViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val binding: SponsorBudgetRecyclerviewItemBinding = SponsorBudgetRecyclerviewItemBinding.bind(view)

        fun bindBudgetViews(budgetItems: SponsorPlanSummaryBudgetDto){
            binding.fragmentSponsorPlanSummaryBudgetItemTv.text = budgetItems.budgetItem
            binding.fragmentSponsorPlanSummaryBudgetItemNameTv.text = budgetItems.budgetName
            binding.fragmentSponsorPlanSummaryBudgetItemAmountTv.text = budgetItems.budgetCost
        }
    }
    fun addBudgetList(list: ArrayList<SponsorPlanSummaryBudgetDto>){
        budgetList.clear()
        budgetList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SponsorPlanSummaryBudgetViewHolder {
        val budgetView = LayoutInflater.from(parent.context).inflate(R.layout.sponsor_budget_recyclerview_item,parent,false)
        return SponsorPlanSummaryBudgetViewHolder(budgetView)
    }

    override fun onBindViewHolder(holder: SponsorPlanSummaryBudgetViewHolder, position: Int) {
        holder.bindBudgetViews(budgetList[position])
    }

    override fun getItemCount(): Int {
        return budgetList.size
    }
}