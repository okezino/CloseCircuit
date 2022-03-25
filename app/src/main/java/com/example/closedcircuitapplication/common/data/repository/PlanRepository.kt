package com.example.closedcircuitapplication.common.data.repository

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.network.models.*
import com.example.closedcircuitapplication.common.domain.repository.PlanRepositoryInterface
import com.example.closedcircuitapplication.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.data.datadto.DeletePlanResponseDto
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import com.example.closedcircuitapplication.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.plan.domain.models.*
import com.example.closedcircuitapplication.plan.presentation.models.GetMyPlansDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlanRepository @Inject constructor(
    private val api: Api,
    private val mapper: DomainPostMapper,
    private val dispatcherProvider: DispatcherProvider
): PlanRepositoryInterface {

    override suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>> = flow {
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
            api.generateOtp(generateOtpRequest)
        })
    }.flowOn(dispatcherProvider.io())

    override suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>> =flow {
        emit(Resource.Loading())
        emit(
            ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
                api.verifyOtp(verifyOtpRequest)
            }
        )
    }.flowOn(dispatcherProvider.io())

    override suspend fun updateUserPlan(updatePlanRequest: UpdatePlanRequest, planId: String, token: String): Flow<Resource<Result<UpdatePlanResponseDto>>> = flow {
        emit(Resource.Loading())
        emit(
            ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
                api.updateUserPlan(updatePlanRequest, planId, token)
            }
        )
    }.flowOn(dispatcherProvider.io())

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


    override suspend fun deletePlan(id: String, token : String): Flow<Resource<Result<DeletePlanResponseDto>>> =
        flow{
            emit(Resource.Loading())
            emit(
                ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
                    api.deletePlan(id, token)
                }
            )
        }.flowOn(dispatcherProvider.io())

    override suspend fun getMyPlans(
        limit: Int,
        offset: Int,
        authHeader: String
    ): Flow<Resource<Result<GetMyPlansDto>>> = flow {
        emit((Resource.Loading()))
        emit(
            ApiCallsHandler.safeApiCall((dispatcherProvider.io())){
                api.getAllPlans(limit, offset, authHeader)
            }
        )
    }.flowOn(dispatcherProvider.io())

    override suspend fun createStep(createStepsRequest: CreateStepsRequest, authHeader: String): Flow<Resource<Result<CreateStepDto>>> =
        flow {
            emit(Resource.Loading())
            emit(
                ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
                    api.createStep(createStepsRequest, authHeader)
                }
            )
        }

    override suspend fun createBudget(createBudgetRequest: CreateBudgetRequest, authHeader: String): Flow<Resource<Result<CreateBudgetDto>>> =
        flow {
            emit(Resource.Loading())
            emit(
                ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                    api.createBudget(createBudgetRequest, authHeader)
                }
            )
        }
}