package com.example.closedcircuitapplication.support.ui.screen

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.AndroidBaseTest
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify

@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HowToUseSupportFragmentTest : AndroidBaseTest() {


    private val mockNavController: NavController = Mockito.mock(NavController::class.java)

    @Test
    fun check_if_UiViews_are_Visible() {

        // Create a graphical FragmentScenario for the loginFragment
        launchFragmentInHiltContainer<HowToUseSupportFragment>{
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        onView(withId(R.id.how_to_use_screen_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.how_to_use_the_closed_circuit_linearLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.how_to_use_closeCircuit_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.what_is_Closed_circuit_Linearlayout)).check(matches(isDisplayed()))
        onView(withId(R.id.whatIs_closedCircuit_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.how_do_i_fund_a_plan_linearLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.how_do_i_fund_a_plan_tv)).check(matches(isDisplayed()))
    }
    @Test
    fun testNavigation_HowToUseSuppotFragmentT0_WhatIsClosedCircuitFragment(){
        launchFragmentInHiltContainer<HowToUseSupportFragment> {
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        onView(withId(R.id.what_is_Closed_circuit_Linearlayout)).perform(click())
        verify(mockNavController).navigate(HowToUseSupportFragmentDirections.actionHowToUseSupportFragmentToWhatIsTheClosedCircuitFragment(), customNavAnimation().build())
    }
}