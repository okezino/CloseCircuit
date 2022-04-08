package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.databinding.FragmentRequestVerificationSuccessfullBinding


class RequestVerificationSuccessfullFragment : Fragment() {

    private  var _binding : FragmentRequestVerificationSuccessfullBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=  FragmentRequestVerificationSuccessfullBinding.inflate(
            inflater,
            container,
            false
        )
        return binding!!.root
    }
}