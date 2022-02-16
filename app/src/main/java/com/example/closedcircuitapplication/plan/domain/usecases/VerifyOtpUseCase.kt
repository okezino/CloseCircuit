package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.data.repository.PlanRepositoryImpl
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(private val planRepositoryImpl: PlanRepositoryImpl) {
    suspend operator fun invoke(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>> =
        planRepositoryImpl.verifyOtp(verifyOtpRequest)
}