package com.example.closedcircuitapplication.support.ui.screen

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.example.closedcircuitapplication.common.utils.AndroidBaseTest
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import org.mockito.Mockito.verify

@MediumTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ComplaintAndIssuesFragmentTest : AndroidBaseTest(){

    private val mockNavController: NavController = Mockito.mock(NavController::class.java)

    @Test
    fun check_if_UiViews_are_Visible() {

        // Create a graphical FragmentScenario for the loginFragment
        launchFragmentInHiltContainer<ComplaintAndIssuesFragment>{
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        onView(withId(R.id.complaint_and_issues_screen_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.how_to_request_change_Linearlayout)).check(matches(isDisplayed()))
        onView(withId(R.id.request_for_change_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.did_not_receive_repayment_linearLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.did_not_receive_repayment_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.unable_to_request_withdrawal_linearLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.unable_to_request_withdrawal_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.speak_to_customer_care_linearLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.speak_to_customer_care_tv)).check(matches(isDisplayed()))
}
    @Test
    fun testNavigation_ComplaintAndIssuesFragmentTo_CustomerCareFragment(){
        launchFragmentInHiltContainer<ComplaintAndIssuesFragment>{
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        onView(withId(R.id.speak_to_customer_care_linearLayout)).perform(click())
        verify(mockNavController).navigate(ComplaintAndIssuesFragmentDirections.actionComplaintAndIssuesFragmentToCustomerCareFragment(), customNavAnimation().build())
    }

}