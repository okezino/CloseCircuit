package com.example.closedcircuitapplication.beneficiary.dashboard.domain.repository

import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.dashboard.data.datadto.UserDetailsResponseDto
import com.example.closedcircuitapplication.beneficiary.dashboard.data.datadto.UserEditProfileResponseDto
import com.example.closedcircuitapplication.beneficiary.dashboard.domain.model.UpdateProfileRequest
import kotlinx.coroutines.flow.Flow

interface DashboardRepositoryInterface {
    suspend fun getUserDetails(userId: String): Flow<Resource<Result<UserDetailsResponseDto>>>

    suspend fun editUserProfile(updateProfileRequest: UpdateProfileRequest, userId: String, token: String): Flow<Resource<Result<UserEditProfileResponseDto>>>
}