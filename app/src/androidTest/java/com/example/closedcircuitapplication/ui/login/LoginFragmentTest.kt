package com.example.closedcircuitapplication.ui.login

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.presentation.ui.screens.LoginFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest : TestCase(){
    private lateinit var scenario: FragmentScenario<LoginFragment>

    @Before
    override fun setUp(){
        scenario = launchFragment(themeResId = R.style.Theme_ClosedCircuitApplication)
        scenario.moveToState(Lifecycle.State.STARTED)

    }
/**
    @Test
    fun testToCheckLoginDetailsMatchInputsEnteredByUser(){
        val email = "phil@gmail.com"
        val password = "emma"
        onView(withId(R.id.fragment_login_email_et)).perform(typeText(email))
        onView(withId(R.id.fragment_login_password_et)).perform(typeText(password))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.fragment_login_btn)).perform(click())
    }
 */
}