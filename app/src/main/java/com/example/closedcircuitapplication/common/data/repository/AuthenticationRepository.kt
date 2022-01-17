package com.example.closedcircuitapplication.common.data.repository

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.authentication.domain.models.Post
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Resource.Error
import com.example.closedcircuitapplication.common.utils.Resource.Success
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val api: Api,
    private val mapper: DomainPostMapper
) : AuthRepository {

    override suspend fun getPosts(): Flow<Resource<List<Post>>> {

        flow {
            emit(Resource.Loading(null))
        }

        return try {
            val result = api.getPosts().map { mapper.mapToDomain(it) }
            flow {
                emit(Success(result))
            }.flowOn(IO)
        } catch (throwable: Throwable) {
            flow {
                emit(Error(throwable.message!!, null))
            }.flowOn(IO)
        }
    }
}