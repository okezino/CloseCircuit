package com.example.closedcircuitapplication.ui.splashScreen

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentSplashScreenBinding

class splashScreen : Fragment(R.layout.fragment_splash_screen) {

    private var _binding : FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater,container,false)

        setUpSplashScreen()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setUpSplashScreen() {
        val closedAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.slide_from_top_animation
        )
        val circuitAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.slide_from_bottom_animation
        )

        binding.closedCircuit.startAnimation(closedAnimation)
//        binding.circuit.startAnimation(circuitAnimation)

        val splashScreenTimeout = 3500
        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingFinished()) {
                findNavController().navigate(R.id.action_splashScreen_to_welcomeScreenFragment)
            } else {
                findNavController().navigate(
                    R.id.action_splashScreen_to_viewPagerFragment, null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.splashScreen, true).build()
                )}
        }, splashScreenTimeout.toLong())
    }


    private fun onBoardingFinished():Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("finished", false)
    }

    override fun onDetach() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}