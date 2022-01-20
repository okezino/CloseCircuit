package com.example.closedcircuitapplication.authentication.domain.models

data class RegisterRequest(
    val email: String,
    val name: String,
    val roles: String?,
    val phone_number: String,
    val password: String,
    val confirm_password: String
)
