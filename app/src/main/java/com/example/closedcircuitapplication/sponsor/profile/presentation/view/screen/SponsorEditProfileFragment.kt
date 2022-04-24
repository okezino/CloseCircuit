package com.example.closedcircuitapplication.sponsor.profile.presentation.view.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentSponsorEditBinding

class SponsorEditProfileFragment : Fragment() {

    private var _binding: FragmentSponsorEditBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentSponsorEditBinding.inflate(inflater, container, false)
        return binding.root
    }

}