package com.example.closedcircuitapplication.dashboard.domain.usecase

import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.DashboardRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.dashboard.data.datadto.UpdateProfileResponseDto
import com.example.closedcircuitapplication.dashboard.data.datadto.UserEditProfileResponseDto
import com.example.closedcircuitapplication.dashboard.domain.model.UpdateProfileRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateProfileUseCae @Inject constructor(private val dashboardRepository: DashboardRepository){
    suspend operator fun invoke(updateProfileRequest: UpdateProfileRequest, userId: String, token: String): Flow<Resource<Result<UserEditProfileResponseDto>>> =
        dashboardRepository.editUserProfile(updateProfileRequest, userId, token)
}