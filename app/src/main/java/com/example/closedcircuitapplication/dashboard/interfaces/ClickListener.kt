package com.example.closedcircuitapplication.dashboard.interfaces

import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem

interface ClickListener {

    fun onClick( budgetDate: StepsBudgetItem)
}