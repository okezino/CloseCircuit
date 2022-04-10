package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.databinding.BudgetItemLayoutBinding
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.AddBudget

class CreateBudgetAdapter(private val listener: BudgetItemClicklistener): RecyclerView.Adapter<CreateBudgetAdapter.CreateBudgetViewHolder>() {

    private var createBudgetList = arrayListOf<AddBudget>()

    inner class CreateBudgetViewHolder(val binding:BudgetItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val deleteButton = binding.budgetItemDeleteBudgetImageView

        fun bind(addBudget: AddBudget) {
            binding.budgetItemBudgetNameTextView.text = addBudget.budgetName
            binding.budgetItemBudgetCostTextView.text = "NGN ${addBudget.budgetCost}"
            binding.budgetItemNumberTextView.text = "Budget Item ${absoluteAdapterPosition + 1}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateBudgetViewHolder {
        val inflater = BudgetItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CreateBudgetViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CreateBudgetViewHolder, position: Int) {
        holder.bind(createBudgetList[position])
        holder.deleteButton.setOnClickListener {
            listener.deleteBudget(createBudgetList[position])
        }
    }

    fun submitList( list: ArrayList<AddBudget>) {
        this.createBudgetList = list
    }

    override fun getItemCount(): Int = createBudgetList.size

}

interface BudgetItemClicklistener {
    fun deleteBudget(budget: AddBudget)
}