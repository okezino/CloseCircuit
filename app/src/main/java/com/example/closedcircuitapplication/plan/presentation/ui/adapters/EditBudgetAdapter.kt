package com.example.closedcircuitapplication.plan.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.common.data.network.models.Budget
import com.example.closedcircuitapplication.databinding.BudgetItemLayoutBinding

class EditBudgetAdapter(private val listeners: BudgetItemClickListeners): RecyclerView.Adapter<EditBudgetAdapter.EditBudgetViewHolder>() {
    private var budgetItems: ArrayList<Budget> = arrayListOf()
    inner class EditBudgetViewHolder(var binding: BudgetItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(budgetItem: Budget) {
            binding.budgetItemNumberTextView.text = "Budget Item ${absoluteAdapterPosition+ 1}"
            binding.budgetItemBudgetCostTextView.text = "NGN ${budgetItem.budget_cost.toString()}"
            binding.budgetItemBudgetNameTextView.text = budgetItem.budget_name
        }
        val deleteBudgetButton = binding.budgetItemDeleteBudgetImageView
        val editBudgetButton = binding.budgetItemEditBudgetImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditBudgetViewHolder {
        val inflater = BudgetItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EditBudgetViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: EditBudgetViewHolder, position: Int) {
        holder.bind(budgetItems[position])
        holder.deleteBudgetButton.setOnClickListener {
            listeners.deleteBudget(budgetItems[position], holder.absoluteAdapterPosition)
        }
        holder.editBudgetButton.setOnClickListener {
            listeners.editBudget(budgetItems[position], holder.absoluteAdapterPosition)
        }
    }

    override fun getItemCount(): Int = budgetItems.size

    fun submitList(list: ArrayList<Budget>) {
        this.budgetItems = list
    }

    fun deleteBudgetItem(budget: Budget, position: Int) {
        budgetItems.forEach {
            if (it.id == budget.id) {
                budgetItems.remove(it)
                this.notifyItemRemoved(position)
            }
        }
    }

    fun updateBudgetItem(budget: Budget, position: Int) {
        budgetItems[position] = budget
        this.notifyItemChanged(position)
    }

    fun addBudgetItem(budget: Budget, position: Int) {
        budgetItems.add(budget)
        this.notifyItemInserted(budgetItems.lastIndex)
    }
}

interface BudgetItemClickListeners {
    fun deleteBudget(budgetItem: Budget, position: Int)
    fun editBudget(budget: Budget, position: Int)
}