package com.example.closedcircuitapplication.sponsor.profile.presentation.view.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentSponsorProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SponsorProfileFragment : Fragment() {
    private var _binding: FragmentSponsorProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding =  FragmentSponsorProfileBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.editSponsorProfileButton.setOnClickListener {
            findNavController().navigate(SponsorProfileFragmentDirections.actionSponsorProfileFragment2ToSponsorEditFragment(),
                customNavAnimation().build()
            )

        }

    }

}