package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.customNavAnimation

class EditEmailVerificationScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_email_verification_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verifyEmailButton = view.findViewById<Button>(R.id.fragment_login_login_btn)
        verifyEmailButton.setOnClickListener {
            findNavController().navigate(EditEmailVerificationScreenFragmentDirections
                .actionEditEmailVerificationScreenFragmentToSuccessfulEmailVerificationScreenFragment(),
                customNavAnimation().build())
        }
    }
}
