package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteBudgetUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(budgetId: String, authHeader: String): Flow<Resource<Unit>> =
        planRepository.deleteBudget(budgetId, authHeader)
}