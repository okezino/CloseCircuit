package com.example.closedcircuitapplication.plan.domain.models

data class UpdateBudgetRequest(
    val budget_name: String,
    val budget_description: String,
    val budget_cost: Float,
    val plan: String,
    val step: String
)
