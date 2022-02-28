package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.dashboard.interfaces.ClickListener
import com.example.closedcircuitapplication.databinding.ProjectItemBinding
import com.example.closedcircuitapplication.plan.presentation.models.GetMyPlansDto
import com.example.closedcircuitapplication.plan.presentation.models.Plan
import com.example.closedcircuitapplication.plan.presentation.models.Projects
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem

class ProjectsAdapter(private val projects: MutableList<Plan>) : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

     lateinit var listener :SetItemClickListener


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
        return ViewHolder(binding)
    }

    fun submitList(list: List<Plan>) = differ.submitList(list)

    override fun getItemCount() = projects.size //differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(projects[position])

        holder.item.setOnClickListener{
            listener.allPlansItemClicked(position)
        }

    }

    // listener : SetItemClickListener
    class ViewHolder(binding: ProjectItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val planName: TextView = binding.planNameTextView
        private val fundsRaised: TextView = binding.fundsRaisedTextView
        private val tasksCompleted: TextView = binding.tasksCompletedTextView
        private val planDescription: TextView = binding.planDescription
        private val tasksCompletedProgress: ProgressBar = binding.tasksCompletedProgressBar
        private val fundsRaisedProgress: ProgressBar = binding.fundsRaisedProgressBar
        val item = binding.root

        fun bindView(project: Plan) {
            planName.text = project.business_name
            planDescription.text = project.plan_description
//            fundsRaised.text = "${project.fundsRaised}% Funds Raised"
//            tasksCompleted.text = "${project.tasksCompleted}% Tasks Completed"
//            tasksCompletedProgress.progress = project.tasksCompleted.toInt()
//            fundsRaisedProgress.progress = project.fundsRaised.toInt()
            item.setOnClickListener {
                val action = ProjectScreenFragmentDirections
                    .actionProjectScreenFragmentToMyStepFragment(StepsBudgetItem(planName.toString(), "30000", "30000"))
                it.findNavController().navigate(action)
            }
           }
        //}
    }

    fun setOnItemClickListener(itemListener : SetItemClickListener){
        listener = itemListener
    }

    interface SetItemClickListener{
        fun allPlansItemClicked(position: Int)
//                fun setOnItemClick(position: Int,myView: View?)
//        fun toggleSaveItemToWishList(position: Int, saveItemTextBox: TextView, saveItemImage: ImageView, item: TopDealAndHotelData)
    }
}

