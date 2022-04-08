package com.example.closedcircuitapplication.dashboard.presentation.ui.interfaces

import com.example.closedcircuitapplication.common.data.network.models.CreateStepDto
import com.example.closedcircuitapplication.common.data.network.models.GetStepsDto
import com.example.closedcircuitapplication.common.data.network.models.Step
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem

interface ClickListener {

    fun onClick( budgetDate: Step)
}