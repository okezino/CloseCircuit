package com.example.closedcircuitapplication.common.domain.repository

import com.example.closedcircuitapplication.common.data.network.models.CreatePlanDto
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import kotlinx.coroutines.flow.Flow

interface PlanRepository {
    suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>>

    suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>>

    suspend fun createPlan(createPlanRequest: CreatePlanRequest, authHeader: String): Flow<Resource<Result<CreatePlanDto>>>
}