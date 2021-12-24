package com.example.closedcircuitapplication.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.dashboard.models.DonationItem
import com.example.closedcircuitapplication.dashboard.models.StepsBudgetItem
import com.example.closedcircuitapplication.databinding.StepsBudgetRecyclerItemBinding

class StepsBudgetAdapter(private val donations: MutableList<StepsBudgetItem>) : RecyclerView.Adapter<StepsBudgetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StepsBudgetRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = donations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(donations[position])
    }

    class ViewHolder(binding: StepsBudgetRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val stepsOrBudgetTitle: TextView = binding.itemTitleTextView
        private val targetFunds: TextView = binding.targetFundsAmtTextView
        private val totalFundsRaised: TextView = binding.fundsRaisedAmtTextView

        fun bindView(item: StepsBudgetItem) {
            stepsOrBudgetTitle.text = item.itemTitle
            targetFunds.text = item.targetFunds
            totalFundsRaised.text = item.totalFundsRaised
        }
    }
}
