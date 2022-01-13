package com.example.closedcircuitapplication.ui.projectScreens

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.databinding.ProjectItemBinding

class ProjectsAdapter(private val projects: MutableList<Projects>) : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = projects.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(projects[position])
    }

    class ViewHolder(binding: ProjectItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val planName: TextView = binding.planNameTextView
        private val fundsRaised: TextView = binding.fundsRaisedTextView
        private val tasksCompleted: TextView = binding.tasksCompletedTextView
        private val planDescription: TextView = binding.planDescription
        private val tasksCompletedProgress: ProgressBar = binding.tasksCompletedProgressBar
        private val fundsRaisedProgress: ProgressBar = binding.fundsRaisedProgressBar
        val item = binding.root

        fun bindView(project: Projects) {
            planName.text = project.projectName
            fundsRaised.text = "${project.fundsRaised}% Funds Raised"
            tasksCompleted.text = "${project.tasksCompleted}% Tasks Completed"
            planDescription.text = project.projectDescription
            tasksCompletedProgress.progress = project.tasksCompleted.toInt()
            fundsRaisedProgress.progress = project.fundsRaised.toInt()
            item.setOnClickListener {
                val action = ProjectScreenFragmentDirections
                    .actionProjectScreenFragmentToCreatePlanFragment(
                        project.planDuration,
                        project.projectDescription,
                        project.projectName,
                        project.planCategory,
                        project.planCategory,
                        ""
                    )
                it.findNavController().navigate(action)
            }
        }
    }
}

