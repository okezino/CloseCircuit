package com.example.closedcircuitapplication.authentication

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.closedcircuitapplication.R
import junit.framework.TestCase
import kotlinx.coroutines.withTimeout
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateAccountFragmentTest : TestCase() {
    lateinit var scenario: FragmentScenario<CreateAccountFragment>

    @Before
     fun setup() {
         scenario = launchFragmentInContainer(themeResId = R.style.Theme_ClosedCircuitApplication)
    }
    @Test
    fun check_if_UiViews_are_Visible(){
        onView(withId(R.id.createAccount_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.fullNameTextInputLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.emailTextInputLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.countrycode)).check(matches(isDisplayed()))
        onView(withId(R.id.phoneNumberTextInputLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.passwordTextInputLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.comfirmPasswordTextInputLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.createAccount_btn)).perform(scrollTo())
        onView(withId(R.id.createAccount_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.alreadyHaveAnAccount_Tv)).perform(scrollTo())
       onView(withId(R.id.alreadyHaveAnAccount_Tv)).check(matches(isDisplayed()))
       onView(withId(R.id.registerUsingGoogle_btn)).perform(scrollTo())
        onView(withId(R.id.registerUsingGoogle_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun check_if_bottons_are_responsive(){
        onView(withId(R.id.createAccount_btn)).perform(scrollTo())
        onView((withId(R.id.createAccount_btn))).perform(click())
        onView(withId(R.id.registerUsingGoogle_btn)).perform(scrollTo())
    }
    @Test
    fun check_if_input_data_is_valide(){
        onView(withId(R.id.fullNameTextInput)).perform(typeText("benjamin"), closeSoftKeyboard())

    }

}