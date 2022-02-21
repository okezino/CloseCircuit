package com.example.closedcircuitapplication.plan.domain.models


import com.google.gson.annotations.SerializedName

data class UpdatePlanRequest(
    @SerializedName("avatar")
    val avatar: String? = null,
    @SerializedName("business_name")
    val businessName: String,
    @SerializedName("estimated_cost_price")
    val estimatedCostPrice: String,
    @SerializedName("estimated_selling_price")
    val estimatedSellingPrice: String,
    @SerializedName("plan_category")
    val planCategory: String,
    @SerializedName("plan_description")
    val planDescription: String,
    @SerializedName("plan_duration")
    val planDuration: String,
    @SerializedName("plan_sector")
    val planSector: String,
    @SerializedName("plan_type")
    val planType: String
)