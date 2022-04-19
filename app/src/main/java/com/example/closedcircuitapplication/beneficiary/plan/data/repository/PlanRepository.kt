package com.example.closedcircuitapplication.beneficiary.plan.data.repository

import com.example.closedcircuitapplication.common.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.common.common.data.network.models.*
import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import com.example.closedcircuitapplication.common.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.DeletePlanResponseDto
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.CreatePlanRequest
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.common.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.*
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.GetMyPlansDto
import com.example.closedcircuitapplication.common.common.data.network.webservice.AuthService
import com.example.closedcircuitapplication.common.common.data.network.webservice.BaseService
import com.example.closedcircuitapplication.common.common.data.repository.ApiCallsHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlanRepository @Inject constructor(
    private val baseApi: BaseService,
    private val authApi: AuthService,
    private val dispatcherProvider: DispatcherProvider
): PlanRepositoryInterface {

    override suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>> = flow {
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
            authApi.generateOtp(generateOtpRequest)
        })
    }.flowOn(dispatcherProvider.io())

    override suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>> =flow {
        emit(Resource.Loading())
        emit(
            ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                authApi.verifyOtp(verifyOtpRequest)
            }
        )
    }.flowOn(dispatcherProvider.io())

    override suspend fun updateUserPlan(updatePlanRequest: UpdatePlanRequest, planId: String): Flow<Resource<Result<UpdatePlanResponseDto>>> = flow {
        emit(Resource.Loading())
        emit(
            ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                baseApi.updateUserPlan(updatePlanRequest, planId)
            }
        )
    }.flowOn(dispatcherProvider.io())

    override suspend fun createPlan(createPlanRequest: CreatePlanRequest): Flow<Resource<Result<CreatePlanDto>>> = flow {
        emit(Resource.Loading())
        emit(
            ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                baseApi.createPlan(createPlanRequest)
            }
        )
    }

    override suspend fun getPlan(planId: String): Flow<Resource<Result<CreatePlanDto>>> = flow {
        emit((Resource.Loading()))
        emit(
            ApiCallsHandler.safeApiCall((dispatcherProvider.io())) {
                baseApi.getPlans(planId)
            }
        )
    }

    override suspend fun deletePlan(id: String): Flow<Resource<Result<DeletePlanResponseDto>>> =
        flow{
            emit(Resource.Loading())
            emit(
                ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                    baseApi.deletePlan(id)
                }
            )
        }.flowOn(dispatcherProvider.io())

    override suspend fun getMyPlans(
        limit: Int,
        offset: Int
    ): Flow<Resource<Result<GetMyPlansDto>>> = flow {
        emit((Resource.Loading()))
        emit(
            ApiCallsHandler.safeApiCall((dispatcherProvider.io())) {
                baseApi.getAllPlans(limit, offset)
            }
        )
    }.flowOn(dispatcherProvider.io())

    override suspend fun createStep(createStepsRequest: CreateStepsRequest): Flow<Resource<Result<CreateStepDto>>> =
        flow {
            emit(Resource.Loading())
            emit(
                ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                    baseApi.createStep(createStepsRequest)
                }
            )
        }

    override suspend fun createBudget(createBudgetRequest: CreateBudgetRequest): Flow<Resource<Result<CreateBudgetDto>>> =
        flow {
            emit(Resource.Loading())
            emit(
                ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                    baseApi.createBudget(createBudgetRequest)
                }
            )
        }

    override suspend fun updateStep(
        stepId: String,
        updateStepRequest: UpdateStepRequest
    ): Flow<Resource<Result<UpdateStepDto>>> =
        flow {
            emit(Resource.Loading())
            emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                baseApi.updateStep(stepId,updateStepRequest)
            })
        }

    override suspend fun updateBudget(
        budgetId: String,
        updateBudgetRequest: UpdateBudgetRequest
    ): Flow<Resource<Result<UpdateBudgetDto>>> = flow {
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
            baseApi.updateBudget(budgetId,updateBudgetRequest)
        })
    }

    override suspend fun deleteStep(
        stepId: String
    ): Flow<Resource<Result<String>>> =
        flow {
            emit(Resource.Loading())
            emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                baseApi.deleteStep(stepId)
            })
        }

    override suspend fun deleteBudget(
        budgetId: String
    ): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                baseApi.deleteBudget(budgetId)
            })
        }

    override suspend fun getUserSteps(): Flow<Resource<Result<GetStepsDto>>> = flow {
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
            baseApi.getUserSteps()
        })
    }

    override suspend fun getUserBudgets(): Flow<Resource<Result<GetBudgetsDto>>> = flow {
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
            baseApi.getUserBudget()
        })
    }


}