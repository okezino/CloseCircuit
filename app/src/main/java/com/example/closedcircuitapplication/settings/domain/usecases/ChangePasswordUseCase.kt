package com.example.closedcircuitapplication.settings.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.domain.repository.SettingsRepositoryInterface
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.settings.data.datadto.ChangePasswordResponseDto
import com.example.closedcircuitapplication.settings.domain.models.ChangePasswordRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCase@Inject constructor(private val settingsRepositoryInterface: SettingsRepositoryInterface) {
    suspend operator fun invoke(changePasswordRequest: ChangePasswordRequest, userId: String, token: String): Flow<Resource<Result<ChangePasswordResponseDto>>> =
        settingsRepositoryInterface.changePassword(changePasswordRequest, userId, token)
}