package com.example.closedcircuitapplication.common.domain.repository

import com.example.closedcircuitapplication.authentication.data.datadto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.datadto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestAuthRepository : AuthRepository {

    private var shouldReturnNetworkError = false
    var token = "737f8kn)u38ewo"
    var resourceMessage = ""
    var resourceError = ""

    val registerResponse = listOf<String>("benjamin@sgmail.com", "benjamin", "Beneficiary", "07012345678")
    val email = "benjamin@sgmail.com"
    val name = "benjamin"
    val roles = "Beneficiary"
    val phone_number = "07012345678"


    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<Result<LoginResponseDto>>> =
        flow {
            emit(Resource.Loading())

            if (shouldReturnNetworkError) {
                emit(Resource.Error(resourceError, null))
            } else {
                emit(
                    Resource.Success(
                        Result(
                            resourceMessage,
                            LoginResponseDto(token),
                            resourceError
                        )
                    )
                )
            }

        }

    override suspend fun register(registerRequest: RegisterRequest): Flow<Resource<Result<RegisterResponseDto>>> =
        flow {
            emit(Resource.Loading())

            if (shouldReturnNetworkError) {
                emit(Resource.Error(resourceError, null))
            }
            else {
                emit(
                    Resource.Success(
                        Result(
                            resourceMessage, RegisterResponseDto(
                                registerRequest.email,
                                registerRequest.fullname,
                                "Beneficiary",
                                registerRequest.phone_number
                            ), resourceError
                        )
                    )
                )
            }
        }

    fun shouldReturnError(value: Boolean, errorMessage: String) {
        shouldReturnNetworkError = value
        resourceError = errorMessage
    }


}