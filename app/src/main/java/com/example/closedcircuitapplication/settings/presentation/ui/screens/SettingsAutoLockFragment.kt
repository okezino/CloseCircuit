package com.example.closedcircuitapplication.settings.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.databinding.FragmentSettingsAutoLockBinding


class SettingsAutoLockFragment : Fragment() {
    private var _binding: FragmentSettingsAutoLockBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsAutoLockBinding.inflate(inflater,container,false)
        return binding.root
    }
}