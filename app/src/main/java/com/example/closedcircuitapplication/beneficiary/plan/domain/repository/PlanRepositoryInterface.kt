package com.example.closedcircuitapplication.beneficiary.plan.domain.repository

import com.example.closedcircuitapplication.common.common.data.network.models.*
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.DeletePlanResponseDto
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.CreatePlanRequest
import com.example.closedcircuitapplication.common.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.*
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.GetMyPlansDto
import kotlinx.coroutines.flow.Flow

interface PlanRepositoryInterface {
    suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>>

    suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>>

    suspend fun createPlan(createPlanRequest: CreatePlanRequest): Flow<Resource<Result<CreatePlanDto>>>

    suspend fun updateUserPlan(updatePlanRequest: UpdatePlanRequest, planId: String): Flow<Resource<Result<UpdatePlanResponseDto>>>

    suspend fun getPlan(planId: String): Flow<Resource<Result<CreatePlanDto>>>

    suspend fun deletePlan(id: String) : Flow<Resource<Result<DeletePlanResponseDto>>>

    suspend fun getMyPlans(limit: Int, offset: Int): Flow<Resource<Result<GetMyPlansDto>>>

    suspend fun createStep(createStepsRequest: CreateStepsRequest): Flow<Resource<Result<CreateStepDto>>>

    suspend fun createBudget(createBudgetRequest: CreateBudgetRequest): Flow<Resource<Result<CreateBudgetDto>>>

    suspend fun updateStep(stepId: String,updateStepRequest: UpdateStepRequest): Flow<Resource<Result<UpdateStepDto>>>

    suspend fun updateBudget(budgetId: String, updateBudgetRequest: UpdateBudgetRequest): Flow<Resource<Result<UpdateBudgetDto>>>

    suspend fun deleteStep(stepId: String): Flow<Resource<Result<String>>>

    suspend fun deleteBudget(budgetId: String): Flow<Resource<Unit>>

    suspend fun getUserSteps(): Flow<Resource<Result<GetStepsDto>>>

    suspend fun getUserBudgets(): Flow<Resource<Result<GetBudgetsDto>>>

}