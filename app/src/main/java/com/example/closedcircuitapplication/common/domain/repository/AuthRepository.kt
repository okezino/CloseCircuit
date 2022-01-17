package com.example.closedcircuitapplication.common.domain.repository

import com.example.closedcircuitapplication.authentication.domain.models.Post
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getPosts(): Flow<Resource<List<Post>>>
}