package com.example.closedcircuitapplication.authentication.domain.models

data class ResetPasswordRequest(
    val email: String,
    val otp: String,
    val new_password: String,
    val confirm_password: String
)
