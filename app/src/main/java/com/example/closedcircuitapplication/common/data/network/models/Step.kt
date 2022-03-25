package com.example.closedcircuitapplication.common.data.network.models

data class Step(
    val id: String,
    val step_name: String,
    val step_description: String,
    val duration: String,
    val target_funds: String,
    val total_funds_raised: String,
    val plan: String,
    val user: String
)
