package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.AndroidBaseTest
import com.example.closedcircuitapplication.common.utils.MockStatus
import com.example.closedcircuitapplication.common.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*



@MediumTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class LoginFragmentTest : AndroidBaseTest() {

    private val email = "abdul@gmail.com"
    private val validPassword = "Password123#"
    private val invalidPassword = "Password123"



    @Test
    fun testSuccessLogin() {

        connectDispatcher(MockStatus.SUCCESS)
        //Launch fragment in isolation
        launchFragmentInHiltContainer<LoginFragment> {}

        onView(withId(R.id.fragment_login_email_tv)).perform(
            typeText(email),
            closeSoftKeyboard()
        )
        onView(withId(R.id.fragment_login_password_tv)).perform(
            typeText(validPassword),
            closeSoftKeyboard()
        )

        onView((withId(R.id.fragment_login_login_btn))).perform(click())

        //TODO

        //1. Test that success dialog is shown

        //2 Test that it navigates to BeneficiaryDashboardActivity

        //Checking for toast
        //onView(withText("otjekjfvkDvysfei8oe)n8rwdw")).inRoot(ToastMatcher())
        // .check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationToFragmentCreateAccount() {
        val mockNavController = mock(NavController::class.java)

        // Create a graphical FragmentScenario for the loginFragment
         launchFragmentInHiltContainer<LoginFragment>{
            Navigation.setViewNavController(requireView(), mockNavController)

        }
        // Verify that performing a click prompts the correct Navigation action
        onView((withId(R.id.fragment_login_create_account_tv))).perform(click())
        verify(mockNavController).navigate(LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment())
    }


    @Test
    fun test_onClickForgetPasswordTextview_navigateTo_FagmentForgetPassword(){
        // Create a mock NavController
        val navController = mock(NavController::class.java)

        // Create a graphical FragmentScenario for the loginFragment
        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        // Verify that performing a click prompts the correct Navigation action
        onView(withId(R.id.fragment_login_forgot_password_tv)).perform(click())
        verify(navController).navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
    }


    @Test
    fun testFailureLogin() {

        connectDispatcher(MockStatus.ERROR)

        //Launch fragment in isolation
        launchFragmentInHiltContainer<LoginFragment> {}

        onView(withId(R.id.fragment_login_email_tv)).perform(
            typeText(email),
            closeSoftKeyboard()
        )
        onView(withId(R.id.fragment_login_password_tv)).perform(
            typeText(validPassword),
            closeSoftKeyboard()
        )

        onView((withId(R.id.fragment_login_login_btn))).perform(click())

        //TODO
        // Test that the error dialog is shown
    }


    @Test
    fun testInvalidPasswordThrowsError() {

        connectDispatcher(MockStatus.SUCCESS)

        launchFragmentInHiltContainer<LoginFragment> {}

        onView(withId(R.id.fragment_login_email_tv)).perform(
            typeText(email),
            closeSoftKeyboard()
        )
        onView(withId(R.id.fragment_login_password_tv)).perform(
            typeText(invalidPassword),
            closeSoftKeyboard()
        )

        onView((withId(R.id.fragment_login_login_btn))).perform(click())

        //TODO( Test that a snackbar is shown saying invalid password)

    }
}