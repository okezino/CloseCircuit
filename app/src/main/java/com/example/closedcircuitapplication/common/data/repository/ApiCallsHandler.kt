package com.example.closedcircuitapplication.common.data.repository

import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Resource.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

object ApiCallsHandler {

    private const val MESSAGE_KEY = "message"
    private const val ERROR_KEY = "error"

    suspend inline fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        crossinline apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(dispatcher) {
            try {
                Success(apiCall.invoke())
            } catch (exception: Exception) {
                when (exception) {
                    is IOException -> Error("IO Error! Could not Connect to the Internet")
                    is HttpException -> {
                        val code = exception.code()
                        val errorResponse = exception.response()?.errorBody()?.let {
                            getErrorMessage(it)
                        }
                        Error("Error $code -> $errorResponse")
                    }
                    else -> {
                        Error("An error has occurred", null)
                    }
                }
            }
        }
    }

    fun getErrorMessage(responseBody: ResponseBody): String {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }

}

