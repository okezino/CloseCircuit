package com.example.closedcircuitapplication.common.data.network.models

class Response<T>(
    val message: String?,
    val data: T?,
    val errors: String?
) {
}