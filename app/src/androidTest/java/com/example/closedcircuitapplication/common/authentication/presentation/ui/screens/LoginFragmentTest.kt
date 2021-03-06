package com.example.closedcircuitapplication.common.authentication.presentation.ui.screens

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.utils.AndroidBaseTest
import com.example.closedcircuitapplication.common.common.utils.MockStatus
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.common.utils.launchFragmentInHiltContainer
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

        val mockNavController = mock(NavController::class.java)
        //Launch fragment in isolation
        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), mockNavController)
        }

        onView(withId(R.id.fragment_login_email_tv)).perform(
            typeText(email),
            closeSoftKeyboard()
        )
        onView(withId(R.id.fragment_login_password_tv)).perform(
            typeText(validPassword),
            closeSoftKeyboard()
        )
        onView((withId(R.id.fragment_login_login_btn))).perform(click())
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
        verify(mockNavController).navigate(LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment(),  customNavAnimation().build())
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
        verify(navController).navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment(), customNavAnimation().build())
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
        onView(withText("Invalid Password")).check(ViewAssertions.matches(isDisplayed()))
        onView(withText("Invalid Password"))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.VISIBLE)))


    }
}