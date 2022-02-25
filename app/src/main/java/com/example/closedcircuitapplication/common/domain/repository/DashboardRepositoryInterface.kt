package com.example.closedcircuitapplication.common.domain.repository

import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.dashboard.data.datadto.UserDetailsResponseDto
import kotlinx.coroutines.flow.Flow

interface DashboardRepositoryInterface {
    suspend fun getUserDetails(userId: String, authHeader: String): Flow<Resource<Result<UserDetailsResponseDto>>>
}