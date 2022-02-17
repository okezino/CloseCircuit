package com.example.closedcircuitapplication.common.data.repository

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.authentication.data.datadto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.datadto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.*
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.data.network.models.ResetPasswordDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Resource.Loading
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val api: Api,
    private val mapper: DomainPostMapper,
    private val dispatcherProvider: DispatcherProvider
) : AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<Result<LoginResponseDto>>> =
        flow {
            emit(Loading())
            emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                api.login(loginRequest)
            })
        }.flowOn(dispatcherProvider.io())



    override suspend fun register(registerRequest: RegisterRequest): Flow<Resource<Result<RegisterResponseDto>>> =
        flow {
            emit(Loading())
            emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
                api.register(registerRequest)
            })
        }.flowOn(dispatcherProvider.io())



    override suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>> =
        flow {
            emit(Loading())
            emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
                api.generateOtp(generateOtpRequest)
            })
        }

    override suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>> =
        flow {
            emit(Loading())
            emit(
                ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
                    api.verifyOtp(verifyOtpRequest)
                }
            )
        }

    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Flow<Resource<Result<ResetPasswordDto>>> =
        flow {
            emit(Loading())
            emit(
                ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
                    api.resetPassword(resetPasswordRequest)
                }
            )
        }

}
