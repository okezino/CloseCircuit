package com.example.closedcircuitapplication.common.common.utils

import com.example.closedcircuitapplication.common.common.data.network.ClosedCircuitApiEndpoints
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class BaseDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/${ClosedCircuitApiEndpoints.LOGIN}" -> MockResponse().setResponseCode(200)

            else -> MockResponse().setResponseCode(400)
        }
    }
}