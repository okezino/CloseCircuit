package com.example.closedcircuitapplication.common.data.network

import com.example.closedcircuitapplication.authentication.data.datadto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.dataDto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.authentication.domain.models.ResetPasswordRequest
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.LOGIN
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.REGISTER
import com.example.closedcircuitapplication.common.data.network.models.*
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface Api {

    @POST(LOGIN)
    suspend fun login(@Body request: LoginRequest): Result<LoginResponseDto>

    @POST(REGISTER)
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResponseDto>

    @POST("generate-otp/")
    suspend fun generateOtp(@Body request: GenerateOtpRequest): Result<GenerateOtpDto>

    @POST("verify-otp/")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): Result<VerifyOtpDto>

    @POST("reset-password/")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Result<ResetPasswordDto>

//    @GET(PLANS)
//    suspend fun getPlans(): Result<>
    @POST("plans/")
    suspend fun createPlan(
        @Body createPlanRequest: CreatePlanRequest,
        @Header("Authorization") authHeader: String
    ): Result<CreatePlanDto>

}