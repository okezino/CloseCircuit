package com.example.closedcircuitapplication.plan.presentation.models

import com.google.gson.annotations.SerializedName

data class CreatePlanRequest(
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("plan_category")
    var plan_category: String,
    @SerializedName("plan_sector")
    var plan_sector: String,
    @SerializedName("plan_type")
    var plan_type: String,
    @SerializedName("business_name")
    var business_name: String,
    @SerializedName("plan_description")
    var plan_description: String,
    @SerializedName("plan_duration")
    var plan_duration: String,
    @SerializedName("estimated_selling_price")
    var estimated_selling_price: String,
    @SerializedName("estimated_cost_price")
    var estimated_cost_price: String
)