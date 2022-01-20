package com.example.closedcircuitapplication.authentication.data.dataDto

data class RegisterResponseDto(
    val email: String,
    val name: String,
    val roles: String,
    val phone_number: String
)
