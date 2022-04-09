package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.UpdateBudgetRequest
import javax.inject.Inject

class UpdateBudgetUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(budgetId: String, authHeader: String, updateBudgetRequest: UpdateBudgetRequest) =
        planRepository.updateBudget(budgetId, authHeader, updateBudgetRequest)
}