package com.example.closedcircuitapplication.common.data.repository

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.domain.repository.DashboardRepositoryInterface
import com.example.closedcircuitapplication.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.dashboard.data.datadto.UpdateProfileResponseDto
import com.example.closedcircuitapplication.dashboard.data.datadto.UserDetailsResponseDto
import com.example.closedcircuitapplication.dashboard.data.datadto.UserEditProfileResponseDto
import com.example.closedcircuitapplication.dashboard.domain.model.UpdateProfileRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val api: Api,
    private val mapper: DomainPostMapper,
    private val dispatcherProvider: DispatcherProvider
): DashboardRepositoryInterface {

    override suspend fun getUserDetails(
        userId: String,
        authHeader: String
    ): Flow<Resource<Result<UserDetailsResponseDto>>> = flow {
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
            api.getUserDetails(userId, authHeader)
        })
    }.flowOn(dispatcherProvider.io())

    override suspend fun editUserProfile(updateProfileRequest: UpdateProfileRequest, userId: String, token: String
    ): Flow<Resource<Result<UserEditProfileResponseDto>>> = flow {
        emit((Resource.Loading()))
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
            api.updateUserProfile(updateProfileRequest,userId, token)
        })
    }.flowOn(dispatcherProvider.io())
}