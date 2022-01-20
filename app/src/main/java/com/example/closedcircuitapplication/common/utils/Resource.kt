package com.example.closedcircuitapplication.common.utils

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(null, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)

}