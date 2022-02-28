package com.example.closedcircuitapplication.dashboard.domain.usecase

import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.DashboardRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.dashboard.data.datadto.UserDetailsResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(private val dashboardRepository: DashboardRepository) {
    suspend operator fun invoke(userId: String, authHeader: String): Flow<Resource<Result<UserDetailsResponseDto>>> =
        dashboardRepository.getUserDetails(userId, authHeader)
}