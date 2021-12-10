package com.example.closedcircuitapplication.authentication

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.closedcircuitapplication.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeScreenFragmentTest : TestCase(){
    lateinit var scenario :FragmentScenario<WelcomeScreenFragment>

    @Before
     fun setup(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_ClosedCircuitApplication)
    }

    @Test
    fun check_if_UIviews_is_visible(){
        onView(withId(R.id.wellcome_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.hand_emoji)).check(matches(isDisplayed()))
        onView(withId(R.id.quotetextView)).check(matches(isDisplayed()))
        onView(withId(R.id.background_image)).check(matches(isDisplayed()))
    }
    @Test
    fun check_if_Botton_are_responsive(){
        onView(withId(R.id.login_btn)).perform(click())
        onView(withId(R.id.welcomePageCreateAccount_btn)).perform(scrollTo())
        //onView(withId(R.id.wellcomePageCreateAccount_btn)).perform(click())
    }
}