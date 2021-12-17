package com.example.closedcircuitapplication.login

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentLoginBinding
import java.util.*
import kotlin.concurrent.schedule


class LoginFragment : Fragment(R.layout.fragment_login) {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var email: String
    private lateinit var password: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        //navigate to forgot password screen
        binding.fragmentLoginForgotPasswordTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        //navigate back to welcome screen from login screen
        binding.imageView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_welcomeScreenFragment)
        }
        binding.fragmentLoginCreateAccountTv.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
        }

        email = "ben@gmail.com"
        password = "Benjamin@123"

        binding.fragmentLoginBtn.setOnClickListener {

            showPleaseWaitAlertDialog()
            val handler = Handler()
            handler.postDelayed({
                val emailEt = binding.fragmentLoginEmailEt.text.toString()
                val passwordEt = binding.fragmentLoginPasswordEt.text.toString()
                Log.d("USER_INPUTS", "email=> $emailEt and password=> $passwordEt")
                if (emailEt == email && passwordEt == password){
                    showLoginSuccessfulDialog()
                    findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment2)

                }else {
                    showAlertInfoAlert()
                }
            }, 1000)
        }
    }


    private fun showPleaseWaitAlertDialog(){
        val view = View.inflate(context,R.layout.custom_login_wait_dialog,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
        dialog!!.window?.setLayout(width, height)

        Timer().schedule(3000){
            dialog.dismiss()

        }
    }

    private fun showLoginSuccessfulDialog(){
        val view = View.inflate(context,R.layout.cutom_login_successful_dialog,null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
        dialog!!.window?.setLayout(width, height)

        Timer().schedule(3000){
            dialog.dismiss()
        }
    }

    private fun showAlertInfoAlert(){
        val view = View.inflate(context,R.layout.custom_alert_info_dialog,null)
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