package com.example.closedcircuitapplication.plan.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem
import com.example.closedcircuitapplication.databinding.StepsBudgetItemLayoutBinding

class MyStepsBudgetAdapter: RecyclerView.Adapter<MyStepsBudgetAdapter.MyStepsRecyclerViewHolder>() {

    private var adapterList = arrayListOf<StepsBudgetItem>()

    inner class MyStepsRecyclerViewHolder(var binding:StepsBudgetItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(budgetItem: StepsBudgetItem) {
            binding.stepsBudgetNameTextView.text = budgetItem.itemTitle
            binding.stepsBudgetTagetFundsAmountTextView.text = budgetItem.targetFunds
            binding.stepsBudgetTotalFundsRaisedAmountTextView.text = budgetItem.totalFundsRaised
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStepsRecyclerViewHolder {
        val inflater = StepsBudgetItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyStepsRecyclerViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyStepsRecyclerViewHolder, position: Int) {
        holder.bind(adapterList[position])
    }

    override fun getItemCount(): Int = adapterList.size

    fun submitList(list: ArrayList<StepsBudgetItem>) {
        this.adapterList = list
    }
}