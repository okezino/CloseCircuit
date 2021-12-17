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

    //Validate user email pattern
    fun validateEmailPattern(usersEmail: String): Boolean{
        val emailPattern = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])".toRegex()
        if (usersEmail.matches(emailPattern)) {
            return true
        }
        return false
    }

    fun validatePasswordPattern(usersPassword : String) : Boolean{
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-@%\\[}+'!/#$^?:;,(\")~`.*=&{>\\]<_])(?=\\S+$).{8,20}$".toRegex()
        if (usersPassword.matches(passwordPattern)){
            return true
        }
        return false
    }

    fun emptyPassword(usersPassword:String):Boolean{
        if(usersPassword.isEmpty()){
            return true
        }
        return false
    }

    fun emptyEmail(usersEmail: String):Boolean{
        if(usersEmail.isEmpty()){
            return true
        }
        return false
    }


}