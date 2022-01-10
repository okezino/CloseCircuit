package com.example.closedcircuitapplication.dashboard.interfaces

import androidx.navigation.NavController
import com.example.closedcircuitapplication.dashboard.models.StepsBudgetItem

interface ClickListener {

    fun onClick( budgetDate: StepsBudgetItem)
}