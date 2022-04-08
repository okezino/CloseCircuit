package com.example.closedcircuitapplication.common.common.data.network.models


data class Result<T>(
    val message: String?,
    val data: T?,
    val errors: String?
)