package com.example.closedcircuitapplication.common.common.utils

import com.example.closedcircuitapplication.common.authentication.domain.models.RegisterRequest

import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.R

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

    fun validateFullNameInput(name: String): List<String> {
        val result = mutableListOf<String>()
        if (name.contains(DIGITCHARACTER)) {
            result.add("Can't start with numbers")
        }

        if (name.contains(SPECAILCHARAACTERS)) {
            result.add("must not contain special characters")
        }
        return result
    }

    // validate Business name
    fun validatePlanCategory(planCategory: String): Boolean {
        return (planCategory.isNotEmpty())
    }

    //validate Business name

    fun validatePlanSector(planSector: String): Boolean {
        return (planSector.isNotEmpty())
    }

    //validate plan duration
    fun validatePlanName(planName: String): Boolean {
        return (planName.isNotEmpty())
    }

    //validate description
    fun validateDescription(description: String): Boolean {
        return (description.isNotEmpty())
    }

    //validate support
    fun validatePlanDuration(planDuration: String): Boolean {
        return (planDuration.isNotEmpty())
    }
    //validate selling price
    fun validateSellingPrice(sellingPrice: String): Boolean {
        return (sellingPrice.isNotEmpty())
    }
    //validate cost price
    fun validateCostPrice(costPrice: String): Boolean {
        return (costPrice.isNotEmpty())
    }


    // phone_number inputField validation
    fun validatePhone_number(phone_number : String):Boolean{
        return phone_number.length < 10
    }

    fun validatePassword_Equals_confirmPasswword(
        password: String,
        confirmPassword: String
    ): Boolean {
        return password != confirmPassword || password.isEmpty()
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


    fun validateUserProfileInput(user: UserInput): Boolean {
        return (user.fullName.isEmpty() || user.username.isEmpty() || validatePhone_number(user.phone_number) || !validateEmailInput(
            user.emailAddress
        ))
    }
    fun validateAccountData(accountData: RegisterRequest):List<String>{
        val result = mutableListOf<String>()
        val splitName = accountData.fullname.split(" ")
        // check if the full name is entered and is a valid name
        if(splitName.size <2 || splitName.size >3){
            result.add("Enter your full name")
        }else if (!validateEmailInput(accountData.email) ) {
            result.add("cant be empty")
        } else if ( validatePhone_number(accountData.phone_number)){
            result.add("Incomplete number")
        } else if ( validatePassword_Equals_confirmPasswword(accountData.password, accountData.confirm_password)) {
            result.add("Password does not match")
        }
        return result
    }

fun Fragment.validateResetPassword(newPassword: String, confirmPassword: String): Boolean {
    if (Validation.validatePasswordPattern(newPassword) && Validation.validatePasswordPattern(
            confirmPassword
        ) && confirmPassword == newPassword
    ) {
        return true
    }
    if (!Validation.validatePasswordPattern(newPassword)) {
        makeSnackBar(getString(R.string.input_valid_password_text), requireView())
        return false
    }

    if (newPassword != confirmPassword) {
        makeSnackBar(getString(R.string.passwords_do_not_match_text), requireView())
        return false
    }
    return false
}
}
fun validateCreatePlanFields(
    businessName: EditText,
    planDescription: EditText,
    planDuration: EditText,
    sellingPrice: EditText,
    costPrice: EditText,
    context: Fragment
): Boolean {
    if (businessName.text.toString().isEmpty()) {
        context.makeSnackBar(
            context.getString(R.string.Plan_name_must_not_be_empty_text),
            context.requireView()
        )
        return false
    }

    if (planDescription.text.toString().isEmpty()) {
        context.makeSnackBar(
            context.getString(R.string.Plan_description_must_not_be_empty_text),
            context.requireView()
        )
        return false
    }

    if (planDuration.text.toString().isEmpty()) {
        context.makeSnackBar(
            context.getString(R.string.Plan_duraton_must_not_be_empty_text),
            context.requireView()
        )
        return false
    }




    if (sellingPrice.text.toString().isEmpty()) {
        context.makeSnackBar(
            context.getString(R.string.Estimated_selling_price_must_not_be_empty_text),
            context.requireView()
        )
        return false
    }

    if (costPrice.text.toString().isEmpty()) {
        context.makeSnackBar(
            context.getString(R.string.Estimated_cost_must_not_be_empty_text),
            context.requireView()
        )
        return false
    }

    if (costPrice.text.toString().toFloat() > sellingPrice.text.toString().toFloat()) {
        context.makeSnackBar(
            context.getString(R.string.Cost_price_cannot_be_greater_text),
            context.requireView()
        )
        return false
    }
    return true
}
data class AccountData (
    val fullName: String,
    val phone_number: String,
    val password: String,
    val email: String,
    val confirmPassword: String,
        )
data class UserInput(
    val fullName: String,
    val username: String,
    val emailAddress: String,
    val phone_number: String
)