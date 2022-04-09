package com.example.closedcircuitapplication.common.common.domain.repository

import com.example.closedcircuitapplication.common.common.data.network.models.GetUserDetailsDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow


interface DashRepository {
    suspend fun getUserDetails(userId: String, authHeader: String): Flow<Resource<Result<GetUserDetailsDto>>>
}