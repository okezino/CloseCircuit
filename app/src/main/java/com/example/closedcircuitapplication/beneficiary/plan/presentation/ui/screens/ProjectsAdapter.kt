package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.databinding.ProjectItemBinding
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.Plan
import com.example.closedcircuitapplication.beneficiary.plan.utils.onItemClickListener

class ProjectsAdapter(private val projects: MutableList<Plan>) :
    RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    lateinit var mListener: onItemClickListener

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Plan>() {
        override fun areItemsTheSame(oldItem: Plan, newItem: Plan): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Plan, newItem: Plan): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, mListener)
    }

    fun submitList(list: List<Plan>) = differ.submitList(list)

    override fun getItemCount() = projects.size //differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(projects[position])
    }

    // listener : SetItemClickListener
    class ViewHolder(binding: ProjectItemBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        private val planName: TextView = binding.planNameTextView
        private val fundsRaised: TextView = binding.fundsRaisedTextView
        private val tasksCompleted: TextView = binding.tasksCompletedTextView
        private val planDescription: TextView = binding.planDescription
        private val tasksCompletedProgress: ProgressBar = binding.tasksCompletedProgressBar
        private val fundsRaisedProgress: ProgressBar = binding.fundsRaisedProgressBar
        val item = binding.root

        init {

            item.setOnClickListener {
                listener.allPlansItemClicked(adapterPosition)
            }
        }

        fun bindView(project: Plan) {
            planName.text = project.business_name
            planDescription.text = project.plan_description
        }
    }
}

