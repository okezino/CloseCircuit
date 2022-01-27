package com.example.closedcircuitapplication.common.utils

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.closedcircuitapplication.ClosedCircuitApplication

class MockTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?, className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, ClosedCircuitApplication::class.java.name, context)
    }
}