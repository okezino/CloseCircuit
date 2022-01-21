package com.example.closedcircuitapplication.common.domain.repository

import com.example.closedcircuitapplication.authentication.domain.models.Post
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestAuthRepository : AuthRepository {

    val resultFlow: Flow<Resource<List<Post>>> = flow { }

    override suspend fun getPosts(): Flow<Resource<List<Post>>> {
        return resultFlow
    }


    fun successLogin() {

    }

    fun failureLogin() {

    }

    fun ioError() {

    }

}