package com.example.closedcircuitapplication.common.data.network

import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.GENERATE_OTP
import com.example.closedcircuitapplication.authentication.data.dataDto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.dataDto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.authentication.domain.models.ResetPasswordRequest
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.LOGIN
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.PLANS
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.REGISTER
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.RESET_PASSWORD
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.UPDATE_PLAN
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.VERIFY_OTP
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.data.network.models.ResetPasswordDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.plan.domain.models.UpdatePlanRequest
import com.example.closedcircuitapplication.common.data.network.models.*
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import retrofit2.http.*
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface Api {

    @POST(LOGIN)
    suspend fun login(@Body request: LoginRequest): Result<LoginResponseDto>

    @POST(REGISTER)
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResponseDto>

    @POST(GENERATE_OTP)
    suspend fun generateOtp(@Body request: GenerateOtpRequest): Result<GenerateOtpDto>

    @POST(VERIFY_OTP)
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): Result<VerifyOtpDto>

    @POST(RESET_PASSWORD)
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Result<ResetPasswordDto>

    @PUT(UPDATE_PLAN)
    suspend fun updateUserPlan(
        @Body editPlanRequest: UpdatePlanRequest,
        @Path("id") planId: String,
        @Header("Authorization") token: String
    ): Result<UpdatePlanResponseDto>

    @POST("plans/")
    suspend fun createPlan(@Body createPlanRequest: CreatePlanRequest,
        @Header("Authorization") authHeader: String): Result<CreatePlanDto>

    @GET(PLANS)
    suspend fun getPlans(@Path("userId") userId: String, @Header("Authorization")authHeader: String): Result<CreatePlanDto>


}