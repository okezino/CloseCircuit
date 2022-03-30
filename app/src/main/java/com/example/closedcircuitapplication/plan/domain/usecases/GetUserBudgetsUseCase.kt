package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.GetBudgetsDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserBudgetsUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(authHeader: String): Flow<Resource<Result<GetBudgetsDto>>> =
        planRepository.getUserBudgets(authHeader)
}


