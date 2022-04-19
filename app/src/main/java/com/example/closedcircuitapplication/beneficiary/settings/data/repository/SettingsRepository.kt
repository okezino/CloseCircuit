package com.example.closedcircuitapplication.beneficiary.settings.data.repository


import com.example.closedcircuitapplication.beneficiary.settings.domain.repository.SettingsRepositoryInterface
import com.example.closedcircuitapplication.common.common.utils.ChangePasswordResponseType
import com.example.closedcircuitapplication.common.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.settings.domain.models.ChangePasswordRequest
import com.example.closedcircuitapplication.common.common.data.network.webservice.BaseService
import com.example.closedcircuitapplication.common.common.data.repository.ApiCallsHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val api: BaseService,
    private val dispatcherProvider: DispatcherProvider
): SettingsRepositoryInterface {

    override suspend fun changePassword(
        changePasswordRequest: ChangePasswordRequest,
        userId: String
    ): Flow<ChangePasswordResponseType> = flow{
        emit(Resource.Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
            api.changePassword(changePasswordRequest, userId)
        })
    }.flowOn(dispatcherProvider.io())

}