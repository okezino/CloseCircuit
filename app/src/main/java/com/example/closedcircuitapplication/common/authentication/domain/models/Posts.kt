package com.example.closedcircuitapplication.common.authentication.domain.models

data class Posts(
    var userId: Long,
    var id: Long,
    var title: String,
    var body: String
)