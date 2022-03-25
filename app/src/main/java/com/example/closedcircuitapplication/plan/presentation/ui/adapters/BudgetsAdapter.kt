package com.example.closedcircuitapplication.plan.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.databinding.BudgetItemRecyclerviewBinding
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem

class BudgetsAdapter: RecyclerView.Adapter<BudgetsAdapter.BudgetViewHolder>() {

    private var budgetList: ArrayList<StepsBudgetItem> = arrayListOf()

    inner class BudgetViewHolder(val binding: BudgetItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(budgetItem: StepsBudgetItem) {
            binding.budgetItemTitleTextView.text = budgetItem.itemTitle
            binding.budgetItemFundsRaisedAmtTextView.text = budgetItem.totalFundsRaised
            binding.budgetItemTargetFundsTextView.text = budgetItem.targetFunds
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
       val inflater = BudgetItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return BudgetViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        holder.bind(budgetList[position])
    }

    override fun getItemCount(): Int = budgetList.size

    fun submitList(list: ArrayList<StepsBudgetItem>) {
        this.budgetList = list
    }
}