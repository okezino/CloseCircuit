package com.example.closedcircuitapplication.common.common.data.network.models

data class UpdateBudgetDto(
    val id: String,
    val budget_name: String,
    val budget_description: String,
    val budget_cost: Float,
    val plan: String,
    val step: String,
    val user: String
)
