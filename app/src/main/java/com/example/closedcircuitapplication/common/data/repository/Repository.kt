package com.example.closedcircuitapplication.common.data.repository

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.authentication.domain.models.Posts
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.network.models.PostsDto
import com.example.closedcircuitapplication.common.utils.Resource
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api, private val mapper: DomainPostMapper) {

    suspend fun getPosts():Resource<MutableList<Posts>> {
       return try {

            Resource.Success(api.getPosts().map {
                mapper.mapToDomain(it)
            } as MutableList)
        }
        catch (throwable:Throwable){
            Resource.Error(message = throwable.message!!)
        }
    }
}