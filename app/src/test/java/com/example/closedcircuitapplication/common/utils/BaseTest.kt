package com.example.closedcircuitapplication.common.utils

import com.example.closedcircuitapplication.TestNetworkConstant
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

open class BaseTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start(TestNetworkConstant.MOCK_WEB_SERVER)
        mockWebServer.dispatcher = BaseDispatcher()
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}