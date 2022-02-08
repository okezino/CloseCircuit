package com.example.closedcircuitapplication.common.data.network

import com.example.closedcircuitapplication.common.data.network.models.Result
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.closedcircuitapplication.authentication.data.datadto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.datadto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.*
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.data.network.models.ResetPasswordDto
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto

interface Api {

    @POST("login/")
    suspend fun login(@Body request: LoginRequest): Result<LoginResponseDto>

    @POST("register/")
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResponseDto>

    @POST("generate-otp/")
    suspend fun generateOtp(@Body request: GenerateOtpRequest): Result<GenerateOtpDto>

    @POST("verify-otp/")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest ):Result<VerifyOtpDto>

    @POST("reset-password/")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Result<ResetPasswordDto>

}