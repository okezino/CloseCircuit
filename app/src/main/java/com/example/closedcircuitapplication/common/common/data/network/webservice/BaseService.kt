package com.example.closedcircuitapplication.common.common.data.network.webservice

import com.example.closedcircuitapplication.beneficiary.dashboard.data.datadto.UserDetailsResponseDto
import com.example.closedcircuitapplication.beneficiary.dashboard.data.datadto.UserEditProfileResponseDto
import com.example.closedcircuitapplication.beneficiary.dashboard.domain.model.UpdateProfileRequest
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.DeletePlanResponseDto
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.*
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.CreatePlanRequest
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.GetMyPlansDto
import com.example.closedcircuitapplication.beneficiary.settings.data.datadto.ChangePasswordResponseDto
import com.example.closedcircuitapplication.beneficiary.settings.domain.models.ChangePasswordRequest
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.BUDGET
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.CHANGE_PASSWORD
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.DELETE_PLAN
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.GET_MY_PLANS
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.GET_STEP
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.PLAN
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.PLANS
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.STEPS
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.UPDATE_PLAN
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.UPDATE_PROFILE
import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints.USER_DETAILS
import com.example.closedcircuitapplication.common.common.data.network.models.*
import retrofit2.http.*

interface BaseService {

    @PUT(UPDATE_PLAN)
    suspend fun updateUserPlan(
        @Body editPlanRequest: UpdatePlanRequest,
        @Path("id") planId: String
    ): Result<UpdatePlanResponseDto>

    @PUT(UPDATE_PROFILE)
    suspend fun updateUserProfile(
        @Body editProfileRequest: UpdateProfileRequest,
        @Path("id") userId: String
    ): Result<UserEditProfileResponseDto>


    @POST(PLAN)
    suspend fun createPlan(
        @Body createPlanRequest: CreatePlanRequest
    ): Result<CreatePlanDto>

    @GET(PLANS)
    suspend fun getPlans(
        @Path("userId") userId: String,
    ): Result<CreatePlanDto>

    @GET(GET_MY_PLANS)
    suspend fun getAllPlans(
        @Query("limit") limit: Int,
        @Query("offset") offSet: Int
    ): Result<GetMyPlansDto>

    @DELETE(DELETE_PLAN)
    suspend fun deletePlan(
        @Path("id") id: String,
    ): Result<DeletePlanResponseDto>

    @GET(USER_DETAILS)
    suspend fun getUserDetails(
        @Path("id") userId: String,
    ): Result<UserDetailsResponseDto>

    @PUT(CHANGE_PASSWORD)
    suspend fun changePassword(
        @Body changePasswordRequest: ChangePasswordRequest,
        @Path("id") userId: String,
    ): Result<ChangePasswordResponseDto>

    @POST(STEPS)
    suspend fun createStep(
        @Body createStepsRequest: CreateStepsRequest,
    ): Result<CreateStepDto>

    @POST(BUDGET)
    suspend fun createBudget(
        @Body createBudgetRequest: CreateBudgetRequest
    ): Result<CreateBudgetDto>

    @PUT(GET_STEP)
    suspend fun updateStep(
        @Path("id") id: String,
        @Body updateStepRequest: UpdateStepRequest
    ): Result<UpdateStepDto>

    @PUT("budget/{id}/")
    suspend fun updateBudget(
        @Path("id") id: String,
        @Body updateBudgetRequest: UpdateBudgetRequest
    ): Result<UpdateBudgetDto>

    @DELETE("steps/{id}/")
    suspend fun deleteStep(
        @Path("id") id: String
    ): Result<String>

    @HTTP(method = "DELETE", path = "budget/{id}/", hasBody = true)
    suspend fun deleteBudget(
        @Path("id") id: String
    )

    @GET("steps/")
    suspend fun getUserSteps(): Result<GetStepsDto>

    @GET("budget/")
    suspend fun getUserBudget(): Result<GetBudgetsDto>
}