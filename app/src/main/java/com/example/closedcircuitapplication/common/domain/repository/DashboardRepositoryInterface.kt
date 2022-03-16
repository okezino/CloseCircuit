package com.example.closedcircuitapplication.common.domain.repository

import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.dashboard.data.datadto.UpdateProfileResponseDto
import com.example.closedcircuitapplication.dashboard.data.datadto.UserDetailsResponseDto
import com.example.closedcircuitapplication.dashboard.data.datadto.UserEditProfileResponseDto
import com.example.closedcircuitapplication.dashboard.domain.model.UpdateProfileRequest
import kotlinx.coroutines.flow.Flow

interface DashboardRepositoryInterface {
    suspend fun getUserDetails(userId: String, authHeader: String): Flow<Resource<Result<UserDetailsResponseDto>>>

    suspend fun editUserProfile(updateProfileRequest: UpdateProfileRequest, userId: String, token: String): Flow<Resource<Result<UserEditProfileResponseDto>>>
}