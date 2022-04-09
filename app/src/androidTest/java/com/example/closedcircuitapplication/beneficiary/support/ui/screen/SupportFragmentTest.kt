package com.example.closedcircuitapplication.beneficiary.support.ui.screen

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.common.utils.launchFragmentInHiltContainer
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
class SupportFragmentTest : TestCase() {
    private val mockNavController: NavController = Mockito.mock(NavController::class.java)

    public override fun setUp() {
        super.setUp()
    }

    @Test
    fun check_if_UiViews_are_Visible() {

        // Create a graphical FragmentScenario for the loginFragment
        launchFragmentInHiltContainer<SupportFragment>{
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        onView(withId(R.id.support_screen_tv))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.how_to_use_Linearlayout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.how_to_use_screen_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.payments_linearLayout_support_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.payments_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.complaints_linearLayout_support_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.complaints_and_Issues_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testNavigation_SupportFragmentTo_HowtoUseSupportFragment(){

    launchFragmentInHiltContainer<SupportFragment> {
        Navigation.setViewNavController(requireView(), mockNavController)
    }
        onView(withId(R.id.how_to_use_Linearlayout)).perform(click())
        verify(mockNavController).navigate(SupportFragmentDirections.actionSupportFragmentToHowToUseSupportFragment(), customNavAnimation().build())
    }

    @Test
    fun testNavigation_SupportFragmentTo_PaymentSupportFragment(){
        launchFragmentInHiltContainer<SupportFragment> {
            Navigation.setViewNavController(requireView(), mockNavController)
        }

        onView(withId(R.id.payments_linearLayout_support_fragment)).perform(click())
        verify(mockNavController).navigate(SupportFragmentDirections.actionSupportFragmentToPaymentsSupportFragment(), customNavAnimation().build())
    }

    @Test
    fun testNavigation_SupportFragmentTo_ComplaintAndIssuesFragment(){
        launchFragmentInHiltContainer<SupportFragment> {
            Navigation.setViewNavController(requireView(), mockNavController)
        }

        onView(withId(R.id.complaints_linearLayout_support_fragment)).perform(click())
        verify(mockNavController).navigate(SupportFragmentDirections.actionSupportFragmentToComplaintAndIssuesFragment(), customNavAnimation().build())
    }
}