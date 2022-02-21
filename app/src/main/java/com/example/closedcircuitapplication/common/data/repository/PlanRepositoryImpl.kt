package com.example.closedcircuitapplication.common.data.repository

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.network.models.CreatePlanDto
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.domain.repository.PlanRepository
import com.example.closedcircuitapplication.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanRepositoryImpl @Inject constructor(
    private val api: Api,
    private val mapper: DomainPostMapper,
    private val dispatcherProvider: DispatcherProvider
): PlanRepository {

    override suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>> = flow {
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
            api.generateOtp(generateOtpRequest)
        })
    }

    override suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>> =flow {
        emit(Resource.Loading())
        emit(
            ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
                api.verifyOtp(verifyOtpRequest)
            }
        )
    }

    override suspend fun createPlan(createPlanRequest: CreatePlanRequest, authHeader: String): Flow<Resource<Result<CreatePlanDto>>> = flow {
        emit(Resource.Loading())
        emit(
            ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                api.createPlan(createPlanRequest, authHeader)
            }
        )
    }

    override suspend fun getPlan(planId: String, authHeader: String): Flow<Resource<Result<CreatePlanDto>>> = flow {
        emit((Resource.Loading()))
        emit(
            ApiCallsHandler.safeApiCall((dispatcherProvider.io())){
                api.getPlans(planId, authHeader)
            }
        )
    }


}