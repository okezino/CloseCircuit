package com.example.closedcircuitapplication.beneficiary.plan.presentation.models

data class Plan(
    val id: String,
    val avatar: String,
    val plan_category: String,
    val plan_sector: String,
    val plan_type: String,
    val business_name: String,
    val plan_description: String,
    val plan_duration: String,
    val estimated_selling_price: String,
    val estimated_cost_price: String,
    val plan_analytics: String,
    val wallet: String,
    val user: String
)
