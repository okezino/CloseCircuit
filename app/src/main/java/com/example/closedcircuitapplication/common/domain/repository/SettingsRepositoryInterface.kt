package com.example.closedcircuitapplication.common.domain.repository


import com.example.closedcircuitapplication.common.utils.ChangePasswordResponseType
import com.example.closedcircuitapplication.settings.domain.models.ChangePasswordRequest
import kotlinx.coroutines.flow.Flow


interface SettingsRepositoryInterface {

    suspend fun changePassword(changePasswordRequest: ChangePasswordRequest, userId: String, token:String): Flow<ChangePasswordResponseType>
}