package com.example.closedcircuitapplication.beneficiary.plan.utils

import org.junit.Assert.*

import org.junit.Test

class PlanUtilsTest {
    private val email = "iniyealakeret1@gmail.com"
    private val lessThan7Email = "iniyea@gmail.com"
    private val lessThan6Email = "iniye@gmail.com"
    private val lessThan5Email = "iniy@gmail.com"
    private val lessThan4Email = "ini@gmail.com"
    private val lessThan3Email = "in@gmail.com"

    @Test
    fun userEmailDisplayText() {
        val expected = "ini****@gmail.com"
        val actual = PlanUtils.userEmailDisplayText(email)
        assertEquals(expected,actual)
    }
    @Test
    fun `fun to test if the user email length is less than 7`() {
        val expected = "in****@gmail.com"
        val actual = PlanUtils.userEmailDisplayText(lessThan7Email)
        assertEquals(expected,actual)
    }
    @Test
    fun `fun to test if the user email length is less than 6`() {
        val expected = "i****@gmail.com"
        val actual = PlanUtils.userEmailDisplayText(lessThan6Email)
        assertEquals(expected,actual)
    }
    @Test
    fun `fun to test if the user email length is less than 5`() {
        val expected = "i***@gmail.com"
        val actual = PlanUtils.userEmailDisplayText(lessThan5Email)
        assertEquals(expected,actual)
    }
    @Test
    fun `fun to test if the user email length is less than 4`() {
        val expected = "i**@gmail.com"
        val actual = PlanUtils.userEmailDisplayText(lessThan4Email)
        assertEquals(expected,actual)
    }
    @Test
    fun `fun to test if the user email length is less than 3`() {
        val expected = "i*@gmail.com"
        val actual = PlanUtils.userEmailDisplayText(lessThan3Email)
        assertEquals(expected,actual)
    }
}