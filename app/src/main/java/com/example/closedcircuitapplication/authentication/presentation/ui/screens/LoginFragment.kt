package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels.AuthenticationViewModel
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Validation
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.dashboard.BeneficiaryDashboardActivity
import com.example.closedcircuitapplication.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var preferences: Preferences
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthenticationViewModel by viewModels()
    lateinit var success_dialog: AlertDialog
    lateinit var waitDialog: AlertDialog
    lateinit var incorrect_emailDialog: AlertDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        initObservers()

        // navigate to forgot password screen
        binding.fragmentLoginForgotPasswordTv.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment(),customNavAnimation().build())
        }

        // navigate back to welcome screen from login screen
        binding.imageView.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeScreenFragment(),
                customNavAnimation().build())
        }
        binding.fragmentLoginCreateAccountTv.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment(),customNavAnimation().build())
        }

        binding.fragmentLoginEmailTv.addTextChangedListener(loginButtonHandler)
        binding.fragmentLoginPasswordTv.addTextChangedListener(loginButtonHandler)

        binding.fragmentLoginLoginBtn.setOnClickListener {

            val email = binding.fragmentLoginEmailTv.text.toString().trim()
            val password = binding.fragmentLoginPasswordTv.text.toString().trim()

            if (Validation.validateEmailPattern(email)) {
                if (Validation.validatePasswordPattern(password)) {
                    viewModel.login(LoginRequest(email, password))

                } else {
                    // call for incorrect password here
                    Snackbar.make(binding.root, "Invalid Password", Snackbar.LENGTH_LONG).show()
                }
            } else {
                // call for incorrect email here
                showAlertInfoAlert()
                Snackbar.make(binding.root, "Invalid email address", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    // Login button handler
    // If the two text fields are empty, the login button will be disabled
    private val loginButtonHandler: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val userLoginEmail: String = binding.fragmentLoginEmailTv.text.toString().trim()
            val userLoginPassword: String = binding.fragmentLoginPasswordTv.text.toString().trim()
            binding.fragmentLoginLoginBtn.isEnabled = Validation.validateEmailInput(userLoginEmail)
                    && userLoginPassword.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    private fun showPleaseWaitAlertDialog() {
        waitDialog = showCustomViewDialog(
            requireContext(), resources,
            R.layout.custom_login_wait_dialog
        )
    }

    private fun showLoginSuccessfulDialog() {

        success_dialog = showCustomViewDialog(
            requireContext(), resources, R.layout.cutom_login_successful_dialog
        )

        Timer().schedule(3000) {
            success_dialog.dismiss()
        }
    }

    // implement alert info for incorrect email and also for incorrect password
    private fun showAlertInfoAlert() {

        val view = View.inflate(context, R.layout.custom_alert_info_dialog, null)

        incorrect_emailDialog = showCustomViewDialog(
            requireContext(), resources, R.layout.custom_alert_info_dialog
        )

        val btnCloseAlertInfo = view.findViewById<ImageView>(R.id.fragment_login_close_icon)

        btnCloseAlertInfo.setOnClickListener {
            incorrect_emailDialog.dismiss()
        }
    }

    private fun initObservers() {
        viewModel.loginResult.observe(viewLifecycleOwner) { resource ->

            when (resource) {
                is Resource.Loading -> {
                    showPleaseWaitAlertDialog()
                }

                is Resource.Success -> {
                    waitDialog.dismiss()  // dismiss the waitDialog
                    showLoginSuccessfulDialog()
                    // this is used to insert the token into the shared preference
                    saveToken(resource.data?.data!!.token)
                    preferences.putToken(resource.datas?.data!!.token)

                    val intentBeneficiaryDashboard =
                        Intent(requireContext(), BeneficiaryDashboardActivity::class.java)
                    startActivity(intentBeneficiaryDashboard)

                }

                is Resource.Error -> {
                    waitDialog.dismiss()
                    Snackbar.make(binding.root, resource.message, Snackbar.LENGTH_LONG)
                        .show()
                    showAlertInfoAlert()
                }
            }
        }
    }

    private fun saveToken(token: String) = preferences.putToken(token)


    override fun onDetach() {
        super.onDetach()
        if (::success_dialog.isInitialized) {
            success_dialog.dismiss()
        }
    }
}
