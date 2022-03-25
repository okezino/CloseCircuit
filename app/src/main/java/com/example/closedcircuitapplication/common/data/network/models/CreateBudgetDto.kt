package com.example.closedcircuitapplication.common.data.network.models

@keep data class CreateBudgetDto(
    val id: String,
    val budget_name: String,
    val budget_description: String,
    val budget_cost: Float,
    val plan: String,
    val step: String,
    val user: String
)

