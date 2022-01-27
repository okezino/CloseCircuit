package com.example.closedcircuitapplication.common.utils

import androidx.core.text.isDigitsOnly

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

    val UPPERCASE = Regex("[A-Z]")
    val LOWERCASE = Regex("[a-z]")
    val DIGITCHARACTER = Regex("[0-9]")
    val SPECAILCHARAACTERS = Regex("[@#\$%^&+=*_-]")

    fun validateEmailInput(email: String): Boolean {
        if (email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
            return false
        }
        return true
    }

    //Validate user email pattern
    fun validateEmailPattern(usersEmail: String): Boolean {
        val emailPattern =
            "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])".toRegex()
        if (usersEmail.matches(emailPattern)) {
            return true
        }
        return false
    }

    //Validate user email pattern
    fun validatePasswordPattern(usersPassword: String): Boolean {
        val passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-@%\\[}+'!/#$^?:;,(\")~`.*=&{>\\]<_])(?=\\S+$).{8,20}$".toRegex()
        if (usersPassword.matches(passwordPattern)) {
            return true
        }
        return false
    }

    fun validateFullNameInput(name: String): List<String>{
        val result = mutableListOf<String>()
        if (name.contains(DIGITCHARACTER)){
            result.add("Can't start with numbers")
        }

        if (name.contains(SPECAILCHARAACTERS)) {
            result.add("must not contain special characters")
        }
        return  result
    }

    // validate Business name
    fun validateBusinessType(businessType: String): Boolean {
        return (businessType.isNotEmpty())
    }

    //validate Business name
    fun validateBusinessName(businessName: String): Boolean {
        return (businessName.isNotEmpty())
    }

    //validate plan duration
    fun validatePlanDuration(planDuration: String): Boolean {
        return (planDuration.isNotEmpty())
    }

    //validate minimum loan range
    fun validateMinimumLoan(minimumRange: String): Boolean {
        return (minimumRange.isNotEmpty())
    }

    //validate maximum loan range
    fun validateMaximumLoan(maximumRange: String): Boolean {
        return (maximumRange.isNotEmpty())
    }

    // validate maximum number of lenders
    fun validateNumberOfLenders(numberOfLenders: String): Boolean {
        return (numberOfLenders.isNotEmpty())
    }

    //validate description
    fun validateDescription(description: String): Boolean {
        return (description.isNotEmpty())
    }

    //validate support
    fun validateSupport(support: String): Boolean {
        return (support.isNotEmpty())
    }

    // phone_number inputField validation
    fun validatePhone_number(phone_number : String):Boolean{
        return phone_number.length < 9
    }


    // passwordInputField validation
    fun validatePasswordErrors(
        password: String,
    ): List<String> {

        val result = mutableListOf<String>()

        if (password.length <= 7) {
            result.add("* Minimum of 8 characters")
        }
        if (!password.contains(UPPERCASE) || !password.contains(LOWERCASE)
        ) {
            result.add("* Uppercase and lowercase")
        }
        if (!password.contains(DIGITCHARACTER)) {
            result.add("* Numbers")
        }

        if (!password.contains(SPECAILCHARAACTERS)) {
            result.add("* Special characters")
        }

        return result

    }
}

data class ValidateCreateAccount(
    val fullName: Boolean = false,
    val email: Boolean = false,
    val phoneNumber: Boolean = false,
    val password: Boolean = false,
    val confirmPassword: Boolean = false
)