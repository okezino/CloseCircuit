package com.example.closedcircuitapplication.ui.onBoardingScreen.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentFourthScreenBinding

class FourthScreenFragment : Fragment(R.layout.fragment_fourth_screen) {

    private var _binding: FragmentFourthScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentFourthScreenBinding.inflate(inflater, container, false)


        val viewPager = activity?.findViewById<ViewPager2>(R.id.onBoardingViewPager)

        //user navigates from the on-boarding screen to login/create account screen
        binding.getStarted.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_welcomeScreenFragment2)
            onBoardingFinished()
        }

        return  binding.root
    }


    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("finished",true)
        editor.apply()
    }

}