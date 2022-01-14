package com.example.closedcircuitapplication.authentication.domain.usecases

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.authentication.domain.models.Posts
import com.example.closedcircuitapplication.common.data.repository.Repository
import com.example.closedcircuitapplication.common.utils.Resource
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Resource<MutableList<Posts>> = repository.getPosts()



}