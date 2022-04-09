package com.example.closedcircuitapplication.common.common.utils

object UserNameSplitterUtils {
    fun userFullName(fullName: String): String {
        val fullNameSplit = fullName.split(" ")
        return fullNameSplit[0]
    }
}


object PhoneNumberSplitter {
    fun phoneNumberCode(phoneNumber: String): String {
        return  phoneNumber.drop(4)
    }
}