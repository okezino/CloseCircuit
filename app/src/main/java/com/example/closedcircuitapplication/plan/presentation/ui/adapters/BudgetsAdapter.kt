package com.example.closedcircuitapplication.plan.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.common.data.network.models.Budget
import com.example.closedcircuitapplication.databinding.BudgetItemRecyclerviewBinding
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem

class BudgetsAdapter: RecyclerView.Adapter<BudgetsAdapter.BudgetViewHolder>() {

    private var budgetList: ArrayList<Budget> = arrayListOf()

    inner class BudgetViewHolder(val binding: BudgetItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(budgetItem: Budget) {
            binding.budgetItemTitleTextView.text = budgetItem.budget_name
            binding.budgetItemFundsRaisedAmtTextView.text = "NGN 0"
            binding.budgetItemTargetFundsTextView.text = "NGN ${budgetItem.budget_cost.toString()}"
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

    fun submitList(list: ArrayList<Budget>) {
        this.budgetList = list
    }
}