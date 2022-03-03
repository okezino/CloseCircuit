package com.example.closedcircuitapplication.plan.utils

object PlanUtils {
    fun userEmailDisplayText(email: String): String {
        var displayEmail = ""
        val userEmailSplit = email.split("@")
        val userEmailEnd = userEmailSplit[1]
        var userSubEmail: String
        if (userEmailSplit[0].length >= 7){
             userSubEmail = email.substring(0, 3)
            displayEmail = "$userSubEmail****@$userEmailEnd"
        }
        if (userEmailSplit[0].length < 7){
             userSubEmail = email.substring(0, 2)
            displayEmail = "$userSubEmail****@$userEmailEnd"
        }
        if (userEmailSplit[0].length in 5..5){
             userSubEmail = email[0].toString()
            displayEmail = "$userSubEmail****@$userEmailEnd"

        }
        if (userEmailSplit[0].length in 4..4){
            userSubEmail = email[0].toString()
            displayEmail = "$userSubEmail***@$userEmailEnd"
        }
        if (userEmailSplit[0].length in 3..3){
             userSubEmail = email[0].toString()
            displayEmail = "$userSubEmail**@$userEmailEnd"
        }
        if (userEmailSplit[0].length in 2..2){
            userSubEmail = email[0].toString()
            displayEmail = "$userSubEmail*@$userEmailEnd"
        }
        return displayEmail
    }

    fun planDescriptionMaxWords(description: String): Int {
        val maxWords: String = description.replace("\n", " ")
        val descriptionText: List<String> = maxWords.split(" ")
        return descriptionText.size
    }
}