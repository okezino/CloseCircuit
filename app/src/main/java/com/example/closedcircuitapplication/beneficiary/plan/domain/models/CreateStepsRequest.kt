package com.example.closedcircuitapplication.beneficiary.plan.domain.models

data class CreateStepsRequest(
    val step_name: String,
    val step_description: String,
    val duration: String,
    val target_funds: Int,
    val plan: String,
    val user: String
)
