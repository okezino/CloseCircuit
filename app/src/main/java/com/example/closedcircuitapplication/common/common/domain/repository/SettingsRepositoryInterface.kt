package com.example.closedcircuitapplication.common.common.domain.repository


import com.example.closedcircuitapplication.common.common.utils.ChangePasswordResponseType
import com.example.closedcircuitapplication.beneficiary.settings.domain.models.ChangePasswordRequest
import kotlinx.coroutines.flow.Flow


interface SettingsRepositoryInterface {

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest, userId: String, token:String): Flow<ChangePasswordResponseType>
}