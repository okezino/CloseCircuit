package com.example.closedcircuitapplication.dashboard.domain

import com.example.closedcircuitapplication.common.data.network.models.GetUserDetailsDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.DashboardRepository
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(
        userId: String,
        authHeader: String
    ): Flow<Resource<Result<GetUserDetailsDto>>> =
        repository.getUserDetails(userId, authHeader)
}