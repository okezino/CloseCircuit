package com.example.closedcircuitapplication.common.data.repository

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.authentication.domain.models.Post
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Resource.Loading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val api: Api,
    private val mapper: DomainPostMapper,
    private val dispatcherProvider: DispatcherProvider
) : AuthRepository {

    override suspend fun getPosts(): Flow<Resource<List<Post>>> = flow {
        emit(Loading())
        emit(ApiCallsHandler.safeApiCall(dispatcherProvider.io()) {
            api.getPosts().map {
                mapper.mapToDomain(it)
            }
        })
    }.flowOn(dispatcherProvider.io())

}