package com.example.closedcircuitapplication.beneficiary.dashboard.domain.usecase

import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.repository.DashboardRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.dashboard.data.datadto.UserEditProfileResponseDto
import com.example.closedcircuitapplication.beneficiary.dashboard.domain.model.UpdateProfileRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateProfileUseCae @Inject constructor(private val dashboardRepository: DashboardRepository) {
    suspend operator fun invoke(updateProfileRequest: UpdateProfileRequest, userId: String, token: String): Flow<Resource<Result<UserEditProfileResponseDto>>> =
        dashboardRepository.editUserProfile(updateProfileRequest, userId, token)
}