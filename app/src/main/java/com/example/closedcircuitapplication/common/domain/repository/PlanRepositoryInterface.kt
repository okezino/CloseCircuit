package com.example.closedcircuitapplication.common.domain.repository

import com.example.closedcircuitapplication.common.data.network.models.*
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.plan.data.datadto.DeletePlanResponseDto
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.plan.domain.models.*
import com.example.closedcircuitapplication.plan.presentation.models.GetMyPlansDto
import kotlinx.coroutines.flow.Flow

interface PlanRepositoryInterface {
    suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>>

    suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>>

    suspend fun createPlan(createPlanRequest: CreatePlanRequest, authHeader: String): Flow<Resource<Result<CreatePlanDto>>>

    suspend fun updateUserPlan(updatePlanRequest: UpdatePlanRequest, planId: String, token: String): Flow<Resource<Result<UpdatePlanResponseDto>>>

    suspend fun getPlan(planId: String, authHeader: String): Flow<Resource<Result<CreatePlanDto>>>

    suspend fun deletePlan(id: String, token :String) : Flow<Resource<Result<DeletePlanResponseDto>>>

    suspend fun getMyPlans(limit: Int, offset: Int, authHeader: String): Flow<Resource<Result<GetMyPlansDto>>>

    suspend fun createStep(createStepsRequest: CreateStepsRequest, authHeader: String): Flow<Resource<Result<CreateStepDto>>>

    suspend fun createBudget(createBudgetRequest: CreateBudgetRequest, authHeader: String): Flow<Resource<Result<CreateBudgetDto>>>

}