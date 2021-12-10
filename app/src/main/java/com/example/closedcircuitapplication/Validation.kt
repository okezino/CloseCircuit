package com.example.closedcircuitapplication

import java.util.regex.Pattern

object Validation {
    var EMAIL_PATTERN = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    fun validateEmailInput(email:String):Boolean{
        if (email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
            return false
        }
        return true
    }
}