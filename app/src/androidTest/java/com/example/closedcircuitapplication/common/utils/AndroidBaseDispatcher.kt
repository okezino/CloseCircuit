package com.example.closedcircuitapplication.common.utils

import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.LOGIN
import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints.REGISTER
import com.example.closedcircuitapplication.common.utils.resources.ErrorJson
import com.example.closedcircuitapplication.common.utils.resources.SuccessJson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class AndroidBaseDispatcher {

    class SuccessDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/$LOGIN" -> MockResponse().setResponseCode(200)
                    .setBody(SuccessJson.loginSuccessJson)
                "/$REGISTER" -> MockResponse().setResponseCode(200)
                    .setBody(SuccessJson.registerSuccessJson)
                else -> MockResponse().setResponseCode(400).setBody(ErrorJson.generic400Json)
            }
        }
    }


    class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/$LOGIN" -> MockResponse().setResponseCode(400)
                    .setBody(ErrorJson.loginErrorJson)
                "/$REGISTER" -> MockResponse().setResponseCode(200)
                    .setBody(SuccessJson.registerSuccessJson)
                else -> MockResponse().setResponseCode(400).setBody(ErrorJson.generic400Json)
            }
        }
    }


}