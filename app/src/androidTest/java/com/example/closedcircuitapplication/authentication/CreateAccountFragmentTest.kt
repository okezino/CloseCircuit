package com.example.closedcircuitapplication.authentication


import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.presentation.ui.screens.CreateAccountFragment
import com.example.closedcircuitapplication.authentication.presentation.ui.screens.CreateAccountFragmentDirections
import com.example.closedcircuitapplication.common.utils.AndroidBaseTest
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify


@MediumTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class CreateAccountFragmentTest : AndroidBaseTest() {

    private val fullName = "Benjamin obeta"
    private val email = "abdul@gmail.com"
    private val validPassword = "Password123#"
    private val comfirmPasword = "Password123#"
    private val invalidPassword = "Password123"
    private val phone_number = "+07039612093"



    @Test
    fun check_if_UiViews_are_Visible() {
        val mockNavController = Mockito.mock(NavController::class.java)

        // Create a graphical FragmentScenario for the loginFragment
        launchFragmentInHiltContainer<CreateAccountFragment>{
            Navigation.setViewNavController(requireView(), mockNavController)
        }
        onView(withId(R.id.createAccount_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.fullNameTextInputLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.emailTextInputLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_create_account_countrycode_picker)).check(matches(isDisplayed()))
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
    fun check_if_bottons_are_responsive() {
        val mockNavController = Mockito.mock(NavController::class.java)
        // Create a graphical FragmentScenario for the loginFragment
        launchFragmentInHiltContainer<CreateAccountFragment>{}

        onView(withId(R.id.createAccount_btn)).perform(scrollTo())
        onView((withId(R.id.createAccount_btn))).perform(click())
        onView(withId(R.id.registerUsingGoogle_btn)).perform(scrollTo())
    }

    @Test
    fun testNavigation_CreateAccountFragmentToLoginFragment(){
    val mockNavController = Mockito.mock(NavController::class.java)

    // Create a graphical FragmentScenario for the loginFragment
    launchFragmentInHiltContainer<CreateAccountFragment>{
        Navigation.setViewNavController(requireView(), mockNavController)
    }
    onView(withId(R.id.fullNameTextInput)).perform(typeText(fullName), closeSoftKeyboard())
    onView(withId(R.id.emailTextInput)).perform(typeText(email), closeSoftKeyboard())
    onView(withId(R.id.phoneNumberTextInput)).perform(typeText(phone_number), closeSoftKeyboard())
    onView(withId(R.id.passwordTextInput)).perform(typeText(validPassword), closeSoftKeyboard())
    onView(withId(R.id.comfirmPasswordTextInput)).perform(typeText(comfirmPasword), closeSoftKeyboard())

    // Verify that performing a click prompts the correct Navigation action
    onView((withId(R.id.createAccount_btn))).perform(scrollTo())
    onView((withId(R.id.createAccount_btn))).perform(click())
    verify(mockNavController).navigate(CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment(),  customNavAnimation().build())
}

}