package com.example.closedcircuitapplication.common.data.repository

import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.domain.repository.SettingsRepositoryInterface
import com.example.closedcircuitapplication.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.settings.data.datadto.ChangePasswordResponseDto
import com.example.closedcircuitapplication.settings.data.mapper.SettingsDtoToDomainMapper
import com.example.closedcircuitapplication.settings.domain.models.ChangePasswordRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val api: Api,
    private val mapper: SettingsDtoToDomainMapper,
    private val dispatcherProvider: DispatcherProvider
): SettingsRepositoryInterface {

    override suspend fun changePassword(
        changePasswordRequest: ChangePasswordRequest,
        userId: String,
        token: String
    ): Flow<Resource<Result<ChangePasswordResponseDto>>> = flow{
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()){
            api.changePassword(changePasswordRequest, userId, token)
        })
    }.flowOn(dispatcherProvider.io())

}