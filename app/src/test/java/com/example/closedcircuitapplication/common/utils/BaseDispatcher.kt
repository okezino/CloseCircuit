package com.example.closedcircuitapplication.common.utils

import com.example.closedcircuitapplication.common.data.network.ClosedCircuitApiEndpoints
import com.example.closedcircuitapplication.common.utils.resources.SuccessJson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class BaseDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/${ClosedCircuitApiEndpoints.LOGIN}" -> MockResponse().setResponseCode(200)
                .setBody(SuccessJson.loginSuccessJson)
            else -> MockResponse().setResponseCode(400)
        }
    }
}