package com.example.closedcircuitapplication.common.data.network

import com.example.closedcircuitapplication.authentication.data.datadto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.datadto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.data.network.models.PostsDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.example.closedcircuitapplication.common.data.network.models.Result

interface Api {

    @POST("login/")
    suspend fun login( @Body request:LoginRequest): Result<LoginResponseDto>

    @POST("register/")
    suspend fun register(@Body request: RegisterRequest) : Result<RegisterResponseDto>
}