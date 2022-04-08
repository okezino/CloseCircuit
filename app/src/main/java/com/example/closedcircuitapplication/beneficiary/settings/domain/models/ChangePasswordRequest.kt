package com.example.closedcircuitapplication.beneficiary.settings.domain.models


import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("confirm_password")
    val confirmPassword: String,
    @SerializedName("old_password")
    val oldPassword: String,
    @SerializedName("password")
    val password: String
)