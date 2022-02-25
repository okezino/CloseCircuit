package com.example.closedcircuitapplication.common.utils

object UserNameSplitterUtils {
    fun userFullName(fullName: String): String {
        val fullNameSplit = fullName.split(" ")
        return fullNameSplit[0]
    }
}