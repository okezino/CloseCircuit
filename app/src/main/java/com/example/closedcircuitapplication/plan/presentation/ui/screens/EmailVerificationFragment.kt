package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.customNavAnimation

class EmailVerificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editEmailAddress = view.findViewById<TextView>(R.id.not_your_email_message_tv)
        val verificationSuccessful = view.findViewById<ImageView>(R.id.envelope_iv)
        editEmailAddress.setOnClickListener {
            findNavController().navigate(EmailVerificationFragmentDirections
                .actionEmailVerificationFragmentToEditEmailVerificationScreenFragment(),
                 customNavAnimation().build())
        }
        verificationSuccessful.setOnClickListener {
            findNavController().navigate(EmailVerificationFragmentDirections
                .actionEmailVerificationFragmentToSuccessfulEmailVerificationScreenFragment(), customNavAnimation().build())
        }
    }
}
