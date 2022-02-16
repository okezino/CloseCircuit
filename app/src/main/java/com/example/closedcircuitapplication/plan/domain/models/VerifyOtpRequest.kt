package com.example.closedcircuitapplication.plan.domain.models

data class VerifyOtpRequest(
    val otp_code: String,
    val email: String
)
