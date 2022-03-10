package com.example.closedcircuitapplication.support.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentHowToUseSuportBinding


class HowToUseSupportFragment : Fragment() {
    private var _binding: FragmentHowToUseSuportBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding= FragmentHowToUseSuportBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            whatIsClosedCircuitLinearlayout.setOnClickListener {
                findNavController().navigate(HowToUseSupportFragmentDirections.actionHowToUseSupportFragmentToWhatIsTheClosedCircuitFragment(), customNavAnimation().build())
            }
        }
    }
}