package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.common.common.data.network.models.Step
import com.example.closedcircuitapplication.beneficiary.dashboard.presentation.ui.interfaces.ClickListener
import com.example.closedcircuitapplication.databinding.StepsBudgetRecyclerItemBinding

class StepsBudgetsAdapter( var listener: ClickListener) : RecyclerView.Adapter<StepsBudgetsAdapter.ViewHolder>() {

    private var items: MutableList<Step> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StepsBudgetRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
        holder.itemView.setOnClickListener {
            listener.onClick(items[position])

        }
    }

    class ViewHolder(binding: StepsBudgetRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val stepsOrBudgetTitle: TextView = binding.itemTitleTextView
        private val targetFunds: TextView = binding.targetFundsAmtTextView
        private val totalFundsRaised: TextView = binding.fundsRaisedAmtTextView

        fun bindView(item: Step) {
            stepsOrBudgetTitle.text = item.step_name
            targetFunds.text = item.target_funds
            totalFundsRaised.text = item.total_funds_raised
        }
    }

    fun submitList(list: ArrayList<Step>) {
        this.items = list
    }
}
