package com.example.closedcircuitapplication.plan.presentation.models

import com.google.gson.annotations.SerializedName

data class CreatePlanRequest(
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("plan_category")
    var planCategory: String,
    @SerializedName("plan_sector")
    var planSector: String,
    @SerializedName("plan_type")
    var planType: String?,
    @SerializedName("business_name")
    var businessName: String,
    @SerializedName("plan_description")
    var planDescription: String,
    @SerializedName("plan_duration")
    var planDuration: String,
    @SerializedName("estimated_selling_price")
    var estimatedSellingPrice: String,
    @SerializedName("estimated_cost_price")
    var estimatedCostPrice: String
)