package com.example.closedcircuitapplication.utils

import com.example.closedcircuitapplication.plan.presentation.models.SponsorsItem
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem

val budgetList = arrayListOf<StepsBudgetItem>(
    StepsBudgetItem("UI/UX Design", "NGN 100,000.00", "NGN 0"),
    StepsBudgetItem("Frontend Development", "NGN 200,000.00", "NGN 0"),
    StepsBudgetItem("Backend Development", "NGN 200,000.00", "NGN 0")
)

val sponsorsList = arrayListOf<SponsorsItem>(
    SponsorsItem("Marvin McKinney", "Loan", "NGN 200,00.00", true),
    SponsorsItem("Robert Fox", "Donation", "NGN 10,000.00", false)
)