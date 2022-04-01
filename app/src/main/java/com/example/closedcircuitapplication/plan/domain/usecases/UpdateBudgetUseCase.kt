package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.plan.domain.models.UpdateBudgetRequest
import javax.inject.Inject

class UpdateBudgetUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(budgetId: String, authHeader: String, updateBudgetRequest: UpdateBudgetRequest) =
        planRepository.updateBudget(budgetId, authHeader, updateBudgetRequest)
}