package com.example.closedcircuitapplication.authentication.data.mappers

import com.example.closedcircuitapplication.authentication.domain.models.Post
import com.example.closedcircuitapplication.common.data.network.models.PostsDto
import com.example.closedcircuitapplication.common.utils.IDomainMapper
import javax.inject.Inject

class DomainPostMapper @Inject constructor() : IDomainMapper<PostsDto, Post> {

    override fun mapToDomain(entity: PostsDto): Post {
        return Post(
            entity.userId,
            entity.id,
            entity.title,
            entity.body
        )
    }
}