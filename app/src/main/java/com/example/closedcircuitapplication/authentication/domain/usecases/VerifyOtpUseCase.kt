package com.example.closedcircuitapplication.authentication.domain.usecases

import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.data.repository.AuthenticationRepository
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke(verifyOtpRequest: VerifyOtpRequest):Flow<Resource<Result<VerifyOtpDto>>> =
        authenticationRepository.verifyOtp(verifyOtpRequest)
}