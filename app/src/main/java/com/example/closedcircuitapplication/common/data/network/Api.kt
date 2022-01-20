package com.example.closedcircuitapplication.common.data.network

import com.example.closedcircuitapplication.authentication.data.dataDto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.data.dataDto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.data.network.models.Result
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("login/")
    suspend fun login(@Body request: LoginRequest): Result<LoginResponseDto>

    @POST("register/")
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResponseDto>
}