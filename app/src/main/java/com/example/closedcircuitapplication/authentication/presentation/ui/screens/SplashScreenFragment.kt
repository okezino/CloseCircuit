package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.FROM_LOGOUT
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logOutExtra = activity?.intent?.extras?.getBoolean(FROM_LOGOUT)
        if (logOutExtra == true) findNavController().navigate(
            SplashScreenFragmentDirections.actionSplashScreenToLoginFragment(),
            customNavAnimation().build()
        )
        else setUpSplashScreen()

    }

    private fun setUpSplashScreen() {
        val closedAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.slide_from_top_animation
        )

        binding.closedCircuit.startAnimation(closedAnimation)

        val splashScreenTimeout = 3500
        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingFinished()) {
                findNavController().navigate(
                    SplashScreenFragmentDirections.actionSplashScreenToViewPagerFragment(),
                    customNavAnimation().build()
                )
            } else {
                findNavController().navigate(
                    SplashScreenFragmentDirections.actionSplashScreenToViewPagerFragment(),
                    customNavAnimation().build()
                )
            }
        }, splashScreenTimeout.toLong())
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("finished", false)
    }

    override fun onDetach() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
    