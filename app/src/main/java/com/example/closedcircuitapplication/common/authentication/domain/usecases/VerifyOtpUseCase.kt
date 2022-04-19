package com.example.closedcircuitapplication.common.authentication.domain.usecases

import com.example.closedcircuitapplication.beneficiary.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.authentication.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(private val authenticationRepository: AuthRepository) {

    suspend operator fun invoke(verifyOtpRequest: VerifyOtpRequest):Flow<Resource<Result<VerifyOtpDto>>> =
        authenticationRepository.verifyOtp(verifyOtpRequest)
}