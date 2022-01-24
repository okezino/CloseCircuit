package com.example.closedcircuitapplication.common.data.network.models


class Result<T>(
    val message: String?,
    val data: T?,
    val errors: String?
)