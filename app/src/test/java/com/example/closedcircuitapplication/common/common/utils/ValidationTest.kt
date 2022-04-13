package com.example.closedcircuitapplication.common.common.utils

import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidationTest {

    val email = "sirqhostarus@gmil.com"
    val password = "Password124#"
    val confirmPassword = "Password124#"
    val passwordWithoutSpecialCharacter = "Password124"
    val passwordWithoutUpperCase = "password124#"
    val passwordWithoutNumber = "Password#"
    val passwordLessThan8character = "Pas1#"

    val phoneNumber = "07012345678"
    val phone_numberLeasThan9 = "070123"

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun validate_wrong_password_returns_false() {
        val expected = false
        val actual = Validation.validateEmailInput("abc")
        assertEquals(expected, actual)
    }

    @Test
    fun validate_right_password_returns_true() {
        val expected = true
        val actual = Validation.validateEmailInput(email)
        assertEquals(expected, actual)
    }

    @Test
    fun validate_empty_password_returns_false() {
        val expected = false
        val actual = Validation.validateEmailInput("")
        assertEquals(expected, actual)
    }


    @Test
    fun validatePassword() {
        val expected = emptyList<String>()
        val actual = Validation.validatePasswordErrors("Password102*")
        assertEquals(expected, actual)
    }

    @Test
    fun validate_Password_input_lessThan_8_returns_Minimum_of_8_characters() {

        val expected = listOf("* Minimum of 8 characters")
        val actual = Validation.validatePasswordErrors(passwordLessThan8character)
        assertEquals(expected, actual)
    }

    @Test
    fun validate_passwordWithoutNumber_Numbers() {
        val expected = listOf("* Numbers")
        val actual = Validation.validatePasswordErrors(passwordWithoutNumber)
        assertEquals(expected, actual)
    }

    @Test
    fun validate_passwordWithoutUpperCase_returns_Uppercase_and_lowercase() {
        val expected = listOf("* Uppercase and lowercase")
        val actual = Validation.validatePasswordErrors(passwordWithoutUpperCase)
        assertEquals(expected, actual)
    }

    @Test
    fun validate_passwordWithoutSpecialCharacter_returns_Special_characters() {
        val expected = listOf("* Special characters")
        val actual = Validation.validatePasswordErrors(passwordWithoutSpecialCharacter)
        assertEquals(expected, actual)
    }

    @Test
    fun validate_phone_numberLessThan_9_digits_returns_true() {
        val actual = Validation.validatePhone_number(phone_numberLeasThan9)
        Assert.assertTrue(actual)
    }

    @Test
    fun validate_fullName_starts_with_digiit_returns_cannot_start_with_digit() {
        val expected = listOf("Can't start with numbers")
        val actual = Validation.validateFullNameInput("123gghh")
        assertEquals(expected, actual)
    }
    @Test
    fun validate_fullName_with_spacial_character() {
        val expected = listOf("must not contain special characters")
        val actual = Validation.validateFullNameInput("ben@@##")
        assertEquals(expected, actual)
    }

    val fullName = "Benjamin"
    val username = "ben"
    val emailAddress= "realben@gmail.com"
    val phone_number = "+2347056"
    @Test
    fun  test_validateUserInput_wrongInput_returns_true(){
        val expected = true
        val actual = Validation.validateUserProfileInput(UserInput(fullName, username, emailAddress, phone_number))
        assertEquals(expected, actual)
    }
}