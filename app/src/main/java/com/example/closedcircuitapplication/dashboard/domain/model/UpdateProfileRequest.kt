package com.example.closedcircuitapplication.dashboard.domain.model

data class UpdateProfileRequest(
    val fullname: String,
    val email: String,
    val phone_number: String
)