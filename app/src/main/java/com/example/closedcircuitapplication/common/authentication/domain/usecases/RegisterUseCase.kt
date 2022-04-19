package com.example.closedcircuitapplication.common.authentication.domain.usecases


import com.example.closedcircuitapplication.common.authentication.data.dataDto.RegisterResponseDto
import com.example.closedcircuitapplication.common.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.authentication.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(registerRequest: RegisterRequest): Flow<Resource<Result<RegisterResponseDto>>> =
        repository.register(registerRequest)

}

