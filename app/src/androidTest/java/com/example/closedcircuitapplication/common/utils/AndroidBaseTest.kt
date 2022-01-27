package com.example.closedcircuitapplication.common.utils

import androidx.test.espresso.IdlingRegistry
import com.example.closedcircuitapplication.common.data.network.AndroidTestNetworkConstant
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
open class AndroidBaseTest {

    lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @get: Rule
    val hiltRule by lazy { HiltAndroidRule(this) }

    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp", okHttpClient
            )
        )
        mockWebServer.start(AndroidTestNetworkConstant.MOCK_WEB_SERVER)

    }

    fun connectDispatcher(status: MockStatus) {
        when (status) {
            MockStatus.SUCCESS -> mockWebServer.dispatcher =
                AndroidBaseDispatcher.SuccessDispatcher()

            MockStatus.ERROR -> mockWebServer.dispatcher =
                AndroidBaseDispatcher.ErrorDispatcher()
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}

enum class MockStatus {
    SUCCESS, ERROR
}