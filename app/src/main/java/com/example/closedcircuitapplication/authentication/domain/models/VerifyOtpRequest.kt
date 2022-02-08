package com.example.closedcircuitapplication.authentication.domain.models

data class VerifyOtpRequest(
    val otp_code: String,
    val email: String
)
