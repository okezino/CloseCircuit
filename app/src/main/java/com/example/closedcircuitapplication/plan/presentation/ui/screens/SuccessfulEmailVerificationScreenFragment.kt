package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
class SuccessfulEmailVerificationScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_successful_email_verification_screen,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val proceedButton = view.findViewById<Button>(R.id.proceed_btn)
        proceedButton.setOnClickListener {
            findNavController().navigate(R.id.action_successfulEmailVerificationScreenFragment_to_createAPlanFragment2)
        }
    }
}