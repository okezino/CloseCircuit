package com.example.closedcircuitapplication.beneficiary.notification.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentNotificationScreenBinding

class NotificationScreenFragment : Fragment(R.layout.fragment_notification_screen) {

    private var _binding: FragmentNotificationScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotificationScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
}
