package com.example.closedcircuitapplication.dashboard.utils

object DashboardUtils {
    fun userFullName(fullName: String): String {
        val fullNameSplit = fullName.split(" ")
        return fullNameSplit[0]
    }
}