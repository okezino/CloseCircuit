package com.example.closedcircuitapplication.dashboard.domain.model

import java.net.URL

data class UpdateProfileRequest(
    val fullname: String,
    val email: String,
    val phone_number: String,
    val avatar: URL
)