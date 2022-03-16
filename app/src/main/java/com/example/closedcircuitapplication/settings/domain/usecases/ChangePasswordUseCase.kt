package com.example.closedcircuitapplication.settings.domain.usecases


import com.example.closedcircuitapplication.common.domain.repository.SettingsRepositoryInterface
import com.example.closedcircuitapplication.common.utils.ChangePasswordResponseType
import com.example.closedcircuitapplication.settings.domain.models.ChangePasswordRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCase@Inject constructor(private val settingsRepositoryInterface: SettingsRepositoryInterface) {
    suspend operator fun invoke(changePasswordRequest: ChangePasswordRequest, userId: String, token: String): Flow<ChangePasswordResponseType> =
        settingsRepositoryInterface.changePassword(changePasswordRequest, userId, token)
}