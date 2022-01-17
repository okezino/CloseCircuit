package com.example.closedcircuitapplication.authentication.domain.usecases

import com.example.closedcircuitapplication.authentication.domain.models.Post
import com.example.closedcircuitapplication.common.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Post>>> =
        authenticationRepository.getPosts()
}