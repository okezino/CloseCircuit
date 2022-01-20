package com.example.closedcircuitapplication.common.data.network

import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.data.network.models.Result
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.closedcircuitapplication.authentication.data.dataDto.LoginResponseDto2
import com.example.closedcircuitapplication.authentication.data.dataDto.RegisterResponseDto2

interface Api {

    @POST("login/")
    suspend fun login(@Body request: LoginRequest): Result<LoginResponseDto2>

    @POST("register/")
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResponseDto2>
}