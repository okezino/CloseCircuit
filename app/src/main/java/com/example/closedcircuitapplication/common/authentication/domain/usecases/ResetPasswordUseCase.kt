package com.example.closedcircuitapplication.common.authentication.domain.usecases

import com.example.closedcircuitapplication.common.authentication.domain.models.ResetPasswordRequest
import com.example.closedcircuitapplication.common.common.data.network.models.ResetPasswordDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.repository.AuthenticationRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {
    suspend operator fun invoke(resetPasswordRequest: ResetPasswordRequest): Flow<Resource<Result<ResetPasswordDto>>> =
        authenticationRepository.resetPassword(resetPasswordRequest)
}
