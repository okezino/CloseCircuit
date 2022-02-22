package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.data.datadto.DeletePlanResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeletePlanUseCaases @Inject constructor( private val deletePlanRepository : PlanRepository){
  suspend  operator fun invoke(id: String, token : String) : Flow<Resource<Result<DeletePlanResponseDto>>>{
   return deletePlanRepository.deletePlan(id, token)
  }
}
