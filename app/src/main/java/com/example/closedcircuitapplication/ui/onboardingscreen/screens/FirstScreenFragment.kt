package com.example.closedcircuitapplication.ui.onBoardingScreen.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentFirstScreenBinding
import androidx.navigation.fragment.findNavController


class FirstScreenFragment : Fragment(R.layout.fragment_first_screen) {


    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentFirstScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(com.example.closedcircuitapplication.R.id.onBoardingViewPager)

        binding.nextBtn.setOnClickListener {
            viewPager?.currentItem = 1
        }

        binding.skipBtn.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_welcomeScreenFragment2)
        }


        return  binding.root
    }

}