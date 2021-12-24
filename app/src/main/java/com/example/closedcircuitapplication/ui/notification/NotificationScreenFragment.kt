package com.example.closedcircuitapplication.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentNotificationScreenBinding

class NotificationScreenFragment : Fragment(R.layout.fragment_notification_screen) {

    private var _binding: FragmentNotificationScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentNotificationScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

}