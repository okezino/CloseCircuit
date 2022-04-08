package com.example.closedcircuitapplication.common.common.data.network.models

import com.google.gson.annotations.SerializedName

data class GetUserDetailsDto(
    val country: String,
    val created_at: String,
    val email: String,
    @SerializedName("fullname")
    val fullName: String,
    val id: String,
    @SerializedName("is_beneficiary")
    val isBeneficiary: Boolean,
    @SerializedName("is_sponsor")
    val isSponsor: Boolean,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("updated_at")
    val updatedAt: String
)