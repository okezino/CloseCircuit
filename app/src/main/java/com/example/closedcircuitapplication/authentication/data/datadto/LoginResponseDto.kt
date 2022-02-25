package com.example.closedcircuitapplication.authentication.data.dataDto

import com.google.gson.annotations.SerializedName


data class LoginResponseDto(
    val token: String,
    @SerializedName("user_id")
    val userId: String
)


