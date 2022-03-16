package com.example.closedcircuitapplication.support.ui.screen

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.AndroidBaseTest
import com.example.closedcircuitapplication.common.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class PaymentsSupportFragmentTest : AndroidBaseTest(){
    private val mockNavController: NavController = Mockito.mock(NavController::class.java)

    @Test
    fun check_if_UiViews_are_Visible() {

        // Create a graphical FragmentScenario for the loginFragment
        launchFragmentInHiltContainer<PaymentsSupportFragment>{
            Navigation.setViewNavController(requireView(), mockNavController)
        }
         onView(withId(R.id.fragment_payments_header_tv)).check(matches(isDisplayed()))
         onView(withId(R.id.how_do_i_pay_Linearlayout)).check(matches(isDisplayed()))
         onView(withId(R.id.how_to_pay_tv)).check(matches(isDisplayed()))
         onView(withId(R.id.how_to_send_payment_linearLayout)).check(matches(isDisplayed()))
         onView(withId(R.id.how_to_send_payment_tv)).check(matches(isDisplayed()))
         onView(withId(R.id.how_to_withdraw_fund_linearLayout)).check(matches(isDisplayed()))
         onView(withId(R.id.how_to_withdraw_fund_tv)).check(matches(isDisplayed()))
    }
}