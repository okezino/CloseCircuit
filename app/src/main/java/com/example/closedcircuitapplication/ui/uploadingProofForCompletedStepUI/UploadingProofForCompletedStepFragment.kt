package com.example.closedcircuitapplication.ui.uploadingProofForCompletedStepUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentUploadingProofForCompletedStepBinding


class UploadingProofForCompletedStepFragment : Fragment() {
    private var _binding : FragmentUploadingProofForCompletedStepBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUploadingProofForCompletedStepBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
}