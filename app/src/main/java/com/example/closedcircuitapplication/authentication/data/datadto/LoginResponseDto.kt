package com.example.closedcircuitapplication.authentication.data.datadto

import com.example.closedcircuitapplication.authentication.domain.models.LoginResponse

data class LoginResponseDto(
    val token: String
) {
    fun mapToDomain(): LoginResponse {
        return LoginResponse(
            token = token
        )
    }
}

