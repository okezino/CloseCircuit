package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentViewPagerBinding
import com.example.closedcircuitapplication.authentication.presentation.ui.adapter.OnBoardingItemAdapter
import com.example.closedcircuitapplication.authentication.presentation.ui.adapter.ViewPagerAdapter

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var onBoardingItemAdapter: OnBoardingItemAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var globalContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)

        onBoardingItemAdapter = OnBoardingItemAdapter()

        globalContext = this.getActivity()

        val fragmentList = arrayListOf<Fragment>(
            FirstScreenFragment(),
            SecondScreenFragment(),
            ThirdScreenFragment(),
            FourthScreenFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        var fragmentManager: FragmentManager? = getFragmentManager()
        fragmentManager!!.beginTransaction();

        viewPagerAdapter = ViewPagerAdapter(fragmentList, fragmentManager, lifecycle)

        binding.onBoardingViewPager.adapter = adapter
        setUpIndicator()
        isCurrentIndicator(0)
        binding.onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                isCurrentIndicator(position)
            }
        })

        return binding.root
    }


    private fun setUpIndicator() {
        val indicators = arrayOfNulls<ImageView>(viewPagerAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        layoutParams.weight = 3.0f
        for (i in indicators.indices) {
            indicators[i] = ImageView(globalContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    globalContext?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.indicator_inactive
                        )
                    }
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorController.addView(indicators[i])
        }
    }

    fun isCurrentIndicator(position: Int) {
        val childCount = binding.indicatorController.childCount

        for (spot in 0 until childCount) {
            val view = binding.indicatorController.getChildAt(spot) as ImageView
            if (spot == position) {
                view.setImageDrawable(
                    globalContext?.let {
                        ContextCompat.getDrawable(it, R.drawable.indicator_active)
                    }
                )
            } else {
                view.setImageDrawable(
                    globalContext?.let {
                        ContextCompat.getDrawable(it, R.drawable.indicator_inactive)
                    }
                )
            }
        }
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onboarding",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("finished",true)
        editor.apply()
    }

}