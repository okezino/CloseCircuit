package com.example.closedcircuitapplication.dashboard.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.closedcircuitapplication.dashboard.CreatePlanBudgetFragment
import com.example.closedcircuitapplication.dashboard.CreatePlanStepsFragment

class CreateStepsBudgetsFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
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
