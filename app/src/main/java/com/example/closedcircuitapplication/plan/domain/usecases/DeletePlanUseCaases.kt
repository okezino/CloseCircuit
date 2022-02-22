package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepositoryImpl
import com.example.closedcircuitapplication.common.domain.repository.PlanRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.data.datadto.DeletePlanResponseDto
import com.example.closedcircuitapplication.plan.domain.models.DeletePlanRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeletePlanUseCaases @Inject constructor( private val deletePlanRepository : PlanRepositoryImpl){
  suspend  operator fun invoke(id: String, token : String) : Flow<Resource<Result<DeletePlanResponseDto>>>{
   return deletePlanRepository.deletePlan(id, token)
  }
}
