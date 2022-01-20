package com.example.closedcircuitapplication.common.domain.repository

import com.example.closedcircuitapplication.authentication.data.datadto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.datadto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.Post
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
 import com.example.closedcircuitapplication.common.data.network.models.Result

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<Result<LoginResponseDto>>>

    suspend fun register(registerRequest: RegisterRequest):Flow<Resource<Result<RegisterResponseDto>>>
}