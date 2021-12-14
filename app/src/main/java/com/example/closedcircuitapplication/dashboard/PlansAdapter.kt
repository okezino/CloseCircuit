package com.example.closedcircuitapplication.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.PlanItemBinding

class PlansAdapter(private val plans: MutableList<PlanItems>) : RecyclerView.Adapter<PlansAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PlanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = plans.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(plans[position])
    }

    class ViewHolder(binding: PlanItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val planName: TextView = binding.planNameTextView
        private val fundsRaised: TextView = binding.fundsRaisedTextView
        private val tasksCompleted: TextView = binding.tasksCompletedTextView
        private val planImage: ImageView = binding.planItemImageView
        private val fundsRaisedProgress: ProgressBar = binding.fundsRaisedProgressBar
        private val tasksCompletedProgress: ProgressBar = binding.tasksCompletedProgressBar

        fun bindView(plan: PlanItems) {
            planName.text = plan.planName
            fundsRaised.text = plan.fundsRaised
            tasksCompleted.text = plan.tasksCompleted
            planImage.setImageDrawable(
                ResourcesCompat.getDrawable(
                    planImage.resources,
                    plan.planImage, null
                )
            )
        }
    }
}
