package com.example.closedcircuitapplication.common.common.data.network.models

data class Budget(
    val id: String,
    var budget_name: String,
    val budget_description: String,
    var budget_cost: Float,
    val plan: String,
    val step: String,
    val user: String
)
