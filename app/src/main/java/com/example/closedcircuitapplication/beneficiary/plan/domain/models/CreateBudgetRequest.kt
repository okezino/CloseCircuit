package com.example.closedcircuitapplication.beneficiary.plan.domain.models

data class CreateBudgetRequest(
    val budget_name: String,
    val budget_description: String,
    val budget_cost: Float,
    val plan: String,
    val step: String
)
