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
                ")+" )

    val UPPERCASE = Regex("[A-Z]")
    val LOWERCASE = Regex("[a-z]")
    val DIGITCHARACTER = Regex("[0-9]")
    val SPECAILCHARAACTERS = Regex("[@#\$%^&+=*_-]")

    fun validateEmailInput(email:String):Boolean{
        if (email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
            return false
        }
        return true
    }

    fun validateFullNameInput(name: String): Boolean {
        val regex = Regex("^([0-9]+([a-z|A-Z]+?)?|([!@#$%&*=|'+,./_-]+)|\\s+|\\+)")
        return name.matches(regex) || name.isEmpty()
    }
}