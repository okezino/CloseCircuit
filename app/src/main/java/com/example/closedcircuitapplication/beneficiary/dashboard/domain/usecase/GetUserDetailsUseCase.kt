package com.example.closedcircuitapplication.beneficiary.dashboard.domain.usecase

import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.repository.DashboardRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.dashboard.data.datadto.UserDetailsResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(private val dashboardRepository: DashboardRepository) {
    suspend operator fun invoke(userId: String, authHeader: String): Flow<Resource<Result<UserDetailsResponseDto>>> =
        dashboardRepository.getUserDetails(userId, authHeader)
}