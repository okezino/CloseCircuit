package com.example.closedcircuitapplication.ui.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.Validation
import com.example.closedcircuitapplication.dashboard.BeneficiaryDashboardActivity
import com.example.closedcircuitapplication.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Timer
import kotlin.concurrent.schedule

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        // navigate to forgot password screen
        binding.fragmentLoginForgotPasswordTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        // navigate back to welcome screen from login screen
        binding.imageView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_welcomeScreenFragment)
        }
        binding.fragmentLoginCreateAccountTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
        }

        binding.emailTv.addTextChangedListener(loginButtonHandler)
        binding.passwordTv.addTextChangedListener(loginButtonHandler)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailTv.text.toString().trim()
            val password = binding.passwordTv.text.toString().trim()

            showPleaseWaitAlertDialog()
            val handler = Handler()
            handler.postDelayed({
                if (Validation.validateEmailPattern(email)) {
                    if (Validation.validatePasswordPattern(password)) {
                        showLoginSuccessfulDialog()
                        val intentBeneficiaryDashboard = Intent(requireContext(), BeneficiaryDashboardActivity::class.java)
                        startActivity(intentBeneficiaryDashboard)
                    } else {
                        // call for incorrect password here
                        showAlertInfoAlert()
                        Snackbar.make(binding.root, "Invalid Password", Snackbar.LENGTH_LONG).show()
                    }
                } else {
                    // call for incorrect email here
                    showAlertInfoAlert()
                    Snackbar.make(binding.root, "Invalid email address", Snackbar.LENGTH_LONG)
                        .show()
                }
            }, 1000)
        }
    }

    // Login button handler
    // If the two text fields are empty, the login button will be disabled
    private val loginButtonHandler: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val userLoginEmail: String = binding.emailTv.text.toString().trim()
            val userLoginPassword: String = binding.passwordTv.text.toString().trim()
            binding.loginBtn.isEnabled =
                userLoginEmail.isNotEmpty() && userLoginPassword.isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    private fun showPleaseWaitAlertDialog() {
        val view = View.inflate(context, R.layout.custom_login_wait_dialog, null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
        dialog!!.window?.setLayout(width, height)

        Timer().schedule(3000) {
            dialog.dismiss()
        }
    }

    private fun showLoginSuccessfulDialog() {
        val view = View.inflate(context, R.layout.cutom_login_successful_dialog, null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
        dialog!!.window?.setLayout(width, height)

        Timer().schedule(3000) {
            dialog.dismiss()
        }
    }

    // implement alert info for incorrect email and also for incorrect password
    private fun showAlertInfoAlert() {
        val view = View.inflate(context, R.layout.custom_alert_info_dialog, null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
        dialog!!.window?.setLayout(width, height)

        val btnCloseAlertInfo = view.findViewById<ImageView>(R.id.fragment_login_close_icon)
        btnCloseAlertInfo.setOnClickListener {
            dialog.dismiss()
        }
    }
}
