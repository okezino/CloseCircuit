package com.example.closedcircuitapplication.common.authentication.domain.repository

import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.authentication.data.dataDto.LoginResponseDto
import com.example.closedcircuitapplication.common.authentication.data.dataDto.RegisterResponseDto
import com.example.closedcircuitapplication.common.authentication.domain.models.*
import com.example.closedcircuitapplication.common.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.common.data.network.models.ResetPasswordDto
import com.example.closedcircuitapplication.common.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.VerifyOtpRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Flow<Resource<Result<LoginResponseDto>>>

    suspend fun register(registerRequest: RegisterRequest): Flow<Resource<Result<RegisterResponseDto>>>

    suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>>

    suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>>

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Flow<Resource<Result<ResetPasswordDto>>>
}