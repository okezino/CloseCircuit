package com.example.closedcircuitapplication

import androidx.navigation.NavController
import com.example.closedcircuitapplication.dashboard.models.StepsBudgetItem

interface ClickListener {

    fun onClick( budgetDate: StepsBudgetItem)
}