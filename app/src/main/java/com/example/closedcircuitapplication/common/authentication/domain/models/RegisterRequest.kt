package com.example.closedcircuitapplication.common.authentication.domain.models

data class RegisterRequest(
    val email : String,
    val fullname :String,
    val roles :String?,
    val phone_number : String,
    val password : String,
    val confirm_password: String
)

