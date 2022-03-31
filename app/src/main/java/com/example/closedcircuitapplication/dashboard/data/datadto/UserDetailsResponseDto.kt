package com.example.closedcircuitapplication.dashboard.data.datadto


import com.google.gson.annotations.SerializedName

data class UserDetailsResponseDto(
    @SerializedName("country")
    val country: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullname")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("is_beneficiary")
    val isBeneficiary: Boolean,
    @SerializedName("is_sponsor")
    val isSponsor: Boolean,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val password: String,
    val confirm_password: String
)