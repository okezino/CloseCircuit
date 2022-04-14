package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.PlansFundedItemsBinding
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.data.PlansFunded

class PlansFundedAdapter(  val context: Context, private val itemList: List<PlansFunded>):RecyclerView.Adapter<PlansFundedAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlansFundedItemsBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(context, itemList[position])
    }

    override fun getItemCount(): Int {
        return 2
    }

    class ViewHolder(binding : PlansFundedItemsBinding):RecyclerView.ViewHolder(binding.root) {
        private val planFundedImage = binding.planFundedImageView
        private val fundType = binding.fundingType
        private val planSector = binding.planSectorTV
        private val amountFunded = binding.amountFundedTv
        private val fundRaised = binding.spFundsRaisedTv
        private val taskCompleted = binding.tasksCompletedTextView
        private val fundRaisedProgress = binding.spFundsRaisedProgressBar
        private val taskCompletedProgress = binding.spTasksCompletedProgressBar

        fun bindView(context: Context, planItem : PlansFunded){
            planSector.text =  context.getString(R.string.plan_sector, planItem.planSector)
            fundType.text = context.getString(R.string.funding_type, planItem.fundingType)
            fundRaised.text = context.getString(R.string.funds_raised,planItem.fundRaised.toString()+"%")
            amountFunded.text = context.getString(R.string.amount_funded, planItem.amountFunded)
            taskCompleted.text= context.getString(R.string.tasks_completed, planItem.taskCompleted.toString()+"%")
            planFundedImage.setImageResource(planItem.planFundedImage)
            taskCompletedProgress.progress = planItem.taskCompleted
            fundRaisedProgress.progress = planItem.fundRaised
        }
    }
}