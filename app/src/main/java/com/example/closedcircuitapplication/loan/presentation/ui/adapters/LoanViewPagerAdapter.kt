package com.example.closedcircuitapplication.loan.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.presentation.models.AdapterModel
import com.example.closedcircuitapplication.authentication.presentation.ui.adapter.OnBoardingItemAdapter
import com.example.closedcircuitapplication.databinding.LoansViewpagerTemplateBinding
import com.example.closedcircuitapplication.loan.presentation.models.LoanStatus

class LoanViewPagerAdapter(private val loansStatus: List<LoanStatus>): RecyclerView.Adapter<LoanViewPagerAdapter.LoanAmountViewHolder>()   {
    class LoanAmountViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding: LoansViewpagerTemplateBinding = LoansViewpagerTemplateBinding.bind(view)
        private val amount: TextView = binding.loanAmount
        private val title: TextView = binding.loanTitle

        fun bind(loans: LoanStatus){
            amount.text = loans.amount
            title.text = loans.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanAmountViewHolder {
        return LoanAmountViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.loans_viewpager_template,
        parent, false))
    }

    override fun onBindViewHolder(holder: LoanAmountViewHolder, position: Int) {
        holder.bind(loansStatus[position])
    }

    override fun getItemCount(): Int {
        return loansStatus.size
    }
}