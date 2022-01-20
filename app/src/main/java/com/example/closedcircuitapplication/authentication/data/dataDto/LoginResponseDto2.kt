package com.example.closedcircuitapplication.authentication.data.dataDto

import com.example.closedcircuitapplication.authentication.domain.models.LoginResponse

data class LoginResponseDto2(
    val token:String
){
    fun mapToDomain():LoginResponse{
        return LoginResponse(
            token = token
        )
    }
}

