package com.example.closedcircuitapplication.beneficiary.support.ui.screen

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.utils.AndroidBaseTest
import com.example.closedcircuitapplication.common.common.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
@MediumTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class LearnAboutUsFragmentTest : AndroidBaseTest(){
    private val mockNavController:NavController = Mockito.mock(NavController::class.java)

    @Test
    fun check_if_UiViews_are_Visible() {
        launchFragmentInHiltContainer<LearnAboutUsFragment> {
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        onView(withId(R.id.what_is_the_closed_circuit_header_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.about_closedCircuit_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.closed_circuit_video)).check(matches(isDisplayed()))
        onView(withId(R.id.closed_circuit_logo_IV)).check(matches(isDisplayed()))
    }
}