package com.example.closedcircuitapplication.authentication.data.mappers

import com.example.closedcircuitapplication.authentication.domain.models.Posts
import com.example.closedcircuitapplication.common.data.network.models.PostsDto
import javax.inject.Inject

class DomainPostMapper () {

        fun mapToDomain(entity: PostsDto): Posts {
            return Posts(
                entity.userId,
                entity.id,
                entity.title,
                entity.body
            )
        }


}