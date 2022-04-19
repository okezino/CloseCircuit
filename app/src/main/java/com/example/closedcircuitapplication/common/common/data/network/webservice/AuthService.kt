package com.example.closedcircuitapplication.common.common.data.network.webservice

import com.example.closedcircuitapplication.beneficiary.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.common.authentication.data.dataDto.LoginResponseDto
import com.example.closedcircuitapplication.common.authentication.data.dataDto.RegisterResponseDto
import com.example.closedcircuitapplication.common.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.common.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.authentication.domain.models.ResetPasswordRequest
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.GENERATE_OTP
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.LOGIN
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.REGISTER
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.RESET_PASSWORD
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.VERIFY_OTP
import com.example.closedcircuitapplication.common.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.common.data.network.models.ResetPasswordDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.network.models.VerifyOtpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
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
}