package com.example.closedcircuitapplication.dashboard.adapter

import android.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.dashboard.CreatePlanBudgetFragment
import com.example.closedcircuitapplication.dashboard.CreatePlanStepsFragment
import com.example.closedcircuitapplication.dashboard.models.BudgetItem
import com.example.closedcircuitapplication.databinding.BudgetStepsItemLayoutBinding

class CreatePlanFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CreatePlanStepsFragment()
            1 -> CreatePlanBudgetFragment()
            else -> CreatePlanStepsFragment()
        }
    }
}

class BudgetStepsPagerAdapter(private var mViews: ArrayList<BudgetItem>) :
    RecyclerView.Adapter<BudgetStepsPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BudgetStepsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount() = mViews.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(mViews[position])
    }

    class ViewHolder(binding: BudgetStepsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val itemName: TextView = binding.addItemTextView
        private val itemInfo: ImageView = binding.contentInfo

        fun bindView(item: BudgetItem) {
            "Add ${item.itemName}".also { itemName.text = it }
            itemInfo.setOnClickListener {
                showInfoDialog(item.itemInfoTitle, item.itemInfoDesc)
            }
        }

        private fun showInfoDialog(itemInfoTitle: String, itemInfoDesc: String) {
            val view = View.inflate(itemView.context, R.layout.custom_item_info_dialog, null)
            val infoTitle = view.findViewById<TextView>(R.id.info_dialog_title)
            infoTitle.text = itemInfoTitle
            val description = view.findViewById<TextView>(R.id.info_dialog_description)
            description.text = itemInfoDesc
            val builder = AlertDialog.Builder(itemView.context)
            builder.setView(view)

            val dialog = builder.create()
            val window = dialog.window
            val wlp = window!!.attributes

            wlp.gravity = Gravity.BOTTOM
            wlp.flags = wlp!!.flags.and(WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv())
            window.attributes = wlp
            dialog.show()

            val closeItemInfoButton = view.findViewById<ImageView>(R.id.close_dialog_icon)
            closeItemInfoButton.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}
