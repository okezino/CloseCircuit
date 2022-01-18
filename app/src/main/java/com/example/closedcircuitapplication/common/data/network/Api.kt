package com.example.closedcircuitapplication.common.data.network

import com.example.closedcircuitapplication.common.data.network.models.PostsDto
import retrofit2.http.GET

interface Api {

    @GET("posts")
    suspend fun getPosts(): List<PostsDto>
}