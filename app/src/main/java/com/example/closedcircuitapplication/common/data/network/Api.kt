package com.example.closedcircuitapplication.common.data.network

import com.example.closedcircuitapplication.authentication.data.dataDto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.dataDto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.authentication.domain.models.ResetPasswordRequest
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.BUDGET
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.CHANGE_PASSWORD
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.DELETE_PLAN
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.GENERATE_OTP
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.GET_MY_PLANS
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.GET_STEP
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.LOGIN
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.PLAN
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.PLANS
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.REGISTER
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.RESET_PASSWORD
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.STEPS
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.UPDATE_PLAN
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.UPDATE_PROFILE
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.USER_DETAILS
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.VERIFY_OTP
import com.example.closedcircuitapplication.common.data.network.models.*
import com.example.closedcircuitapplication.dashboard.data.datadto.UserDetailsResponseDto
import com.example.closedcircuitapplication.dashboard.data.datadto.UserEditProfileResponseDto
import com.example.closedcircuitapplication.dashboard.domain.model.UpdateProfileRequest
import com.example.closedcircuitapplication.plan.data.datadto.DeletePlanResponseDto
import com.example.closedcircuitapplication.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.plan.domain.models.*
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import com.example.closedcircuitapplication.plan.presentation.models.GetMyPlansDto
import com.example.closedcircuitapplication.settings.data.datadto.ChangePasswordResponseDto
import com.example.closedcircuitapplication.settings.domain.models.ChangePasswordRequest
import retrofit2.http.*


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

    @PUT(UPDATE_PROFILE)
    suspend fun updateUserProfile(
        @Body editProfileRequest: UpdateProfileRequest,
        @Path("id") userId: String,
        @Header("Authorization") token: String
    ): Result<UserEditProfileResponseDto>


    @POST(PLAN)
    suspend fun createPlan(
        @Body createPlanRequest: CreatePlanRequest,
        @Header("Authorization") authHeader: String
    ): Result<CreatePlanDto>

    @GET(PLANS)
    suspend fun getPlans(
        @Path("userId") userId: String,
        @Header("Authorization") authHeader: String
    ): Result<CreatePlanDto>

    @GET(GET_MY_PLANS)
    suspend fun getAllPlans(
        @Query("limit") limit: Int,
        @Query("offset") offSet: Int,
        @Header("Authorization") authHeader: String
    ): Result<GetMyPlansDto>

    @DELETE(DELETE_PLAN)
    suspend fun deletePlan(
        @Path("id") id: String,
        @Header("Authorization") authHeader: String
    ): Result<DeletePlanResponseDto>

    @GET(USER_DETAILS)
    suspend fun getUserDetails(
        @Path("id") userId: String,
        @Header("Authorization") authHeader: String
    ): Result<UserDetailsResponseDto>

    @PUT(CHANGE_PASSWORD)
    suspend fun changePassword(
        @Body changePasswordRequest: ChangePasswordRequest,
        @Path("id") userId: String,
        @Header("Authorization") token: String
    ): Result<ChangePasswordResponseDto>

    @POST(STEPS)
    suspend fun createStep(
        @Body createStepsRequest: CreateStepsRequest,
        @Header("Authorization") authHeader: String
    ): Result<CreateStepDto>

    @POST(BUDGET)
    suspend fun createBudget(
        @Body createBudgetRequest: CreateBudgetRequest,
        @Header("Authorization") authHeader: String
    ): Result<CreateBudgetDto>

    @PUT(GET_STEP)
    suspend fun updateStep(
        @Path("id") id: String,
        @Header("Authorization") authHeader: String,
        @Body updateStepRequest: UpdateStepRequest
    ): Result<UpdateStepDto>

    @PUT("budget/{id}/")
    suspend fun updateBudget(
        @Path("id") id: String,
        @Header("Authorization") authHeader: String,
        @Body updateBudgetRequest: UpdateBudgetRequest
    ): Result<UpdateBudgetDto>

    @DELETE("steps/{id}/")
    suspend fun deleteStep(
        @Path("id") id: String,
        @Header("Authorization") authHeader: String
    ): Result<String>

    @HTTP(method = "DELETE", path = "budget/{id}/", hasBody = true)
    suspend fun deleteBudget(
        @Path("id") id: String,
        @Header("Authorization") authHeader: String
    )

    @GET("steps/")
    suspend fun getUserSteps(@Header("Authorization") authHeader: String): Result<GetStepsDto>

    @GET("budget/")
    suspend fun getUserBudget(@Header("Authorization") authHeader: String): Result<GetBudgetsDto>
}