package com.example.closedcircuitapplication.beneficiary.dashboard.data.datasource

import com.example.closedcircuitapplication.common.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.beneficiary.dashboard.domain.repository.DashboardRepositoryInterface
import com.example.closedcircuitapplication.common.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.dashboard.data.datadto.UserDetailsResponseDto
import com.example.closedcircuitapplication.beneficiary.dashboard.data.datadto.UserEditProfileResponseDto
import com.example.closedcircuitapplication.beneficiary.dashboard.domain.model.UpdateProfileRequest
import com.example.closedcircuitapplication.common.common.data.network.webservice.BaseService
import com.example.closedcircuitapplication.common.common.data.repository.ApiCallsHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val api: BaseService,
    private val dispatcherProvider: DispatcherProvider
): DashboardRepositoryInterface {

    override suspend fun getUserDetails(
        userId: String
    ): Flow<Resource<Result<UserDetailsResponseDto>>> = flow {
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
            api.getUserDetails(userId)
        })
    }.flowOn(dispatcherProvider.io())

    override suspend fun editUserProfile(updateProfileRequest: UpdateProfileRequest, userId: String, token: String
    ): Flow<Resource<Result<UserEditProfileResponseDto>>> = flow {
        emit((Resource.Loading()))
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
            api.updateUserProfile(updateProfileRequest, userId)
        })
    }.flowOn(dispatcherProvider.io())
}