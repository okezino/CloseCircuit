package com.example.closedcircuitapplication.common.utils

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidationTest {

    var email = "sirqhostarus@gmil.com"
    var password = "Password124#"
    var confirmPassword = "Password124#"
    var phoneNumber = "07012345678"

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
    fun validateEmailPattern() {
    }

    @Test
    fun validatePasswordPattern() {
    }

    @Test
    fun validateFullNameInput() {
    }

    @Test
    fun validateBusinessType() {
    }

    @Test
    fun validateBusinessName() {
    }

    @Test
    fun validatePlanDuration() {
    }

    @Test
    fun validateMinimumLoan() {
    }

    @Test
    fun validateMaximumLoan() {
    }

    @Test
    fun validateNumberOfLenders() {
    }

    @Test
    fun validateDescription() {
    }

    @Test
    fun validateSupport() {
    }
}