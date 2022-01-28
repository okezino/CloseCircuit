package com.example.closedcircuitapplication.common.data.network.models


data class Result<T>(
    val message: String?,
    val data: T?,
    val errors: String?
)