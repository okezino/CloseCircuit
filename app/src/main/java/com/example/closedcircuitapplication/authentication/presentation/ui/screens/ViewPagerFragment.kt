package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.presentation.models.AdapterModel
import com.example.closedcircuitapplication.authentication.presentation.ui.adapter.OnBoardingItemAdapter
import com.example.closedcircuitapplication.authentication.presentation.ui.adapter.ViewPagerAdapter
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.ONBOARDING
import com.example.closedcircuitapplication.databinding.FragmentViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    @Inject
    lateinit var preferences: Preferences
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var onBoardingItemAdapter: OnBoardingItemAdapter
//    private lateinit var viewPagerAdapter: OnBoardingItemAdapter
    private var globalContext: Context? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)

        onBoardingItemAdapter = OnBoardingItemAdapter(
            listOf(
                AdapterModel(
                    onBoardingImage = R.drawable.ic_idea_illustration,
                    title = "Easily raise funds for your business or project",
                    description = "Start a business or project and get your family, friends or wellwishers to financially support from anywhere in the world."
                ),
                AdapterModel(
                    onBoardingImage = R.drawable.ic_project_illustration,
                    title = "Get guided execution. Create a budget and action steps",
                    description = "Follow the guided steps on execution of your business or project to unlock funds and stay accountable to your sponsors."
                ),
                AdapterModel(
                    onBoardingImage = R.drawable.ic_sponsor_illustration,
                    title = "Support, incubate and mentor with your financial contributions",
                    description = "Ensure your financial contributions are meaningfully used to establish success in the business or project funded."
                ),
                AdapterModel(
                    onBoardingImage = R.drawable.ic_investment_illustration,
                    title = "Discover diligently executed ideas to invest in",
                    description = "Discover investment opportunities in star performing businesses or projects on the platform."
                )

            )
        )

        globalContext = this.activity

//        val fragmentList = arrayListOf(
//            FirstScreenFragment(),
//            SecondScreenFragment(),
//            ThirdScreenFragment(),
//            FourthScreenFragment()
//        )

//        val adapter = ViewPagerAdapter(
//            fragmentList,
//            requireActivity().supportFragmentManager,
//            lifecycle
//        )

//        val fragmentManager: FragmentManager? = getFragmentManager()
//        fragmentManager!!.beginTransaction();
//
//        viewPagerAdapter = OnBoardingItemAdapter(onBoardingItemAdapter)

        binding.onBoardingViewPager.adapter = onBoardingItemAdapter
        setUpIndicator()
        isCurrentIndicator(0)
        binding.onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                isCurrentIndicator(position)
            }
        })

        val onBoardingVp = binding.onBoardingViewPager

        binding.nextBtn.setOnClickListener {
            if (onBoardingVp.currentItem +1 < onBoardingItemAdapter.itemCount){
                onBoardingVp.currentItem +=1

            }else{
                navigateToLoginFragment()
                onBoardingFinished()
            }
        }

        binding.skipBtn.setOnClickListener {
            navigateToLoginFragment()
            onBoardingFinished()
        }

        binding.getStarted.setOnClickListener {
            navigateToLoginFragment()
            onBoardingFinished()
        }

        return binding.root
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.loginFragment)
        onBoardingFinished()
    }

    private fun buttonVisibility(){
        binding.skipBtn.isVisible =  false
        binding.nextBtn.isVisible = false
        binding.getStarted.isVisible = true
    }

    private fun buttonVisibility2(){
        binding.skipBtn.isVisible =  true
        binding.nextBtn.isVisible = true
        binding.getStarted.isVisible = false
    }


    private fun setUpIndicator() {
        val indicators = arrayOfNulls<ImageView>(onBoardingItemAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        layoutParams.weight = 3.0f
        for (i in indicators.indices) {
            indicators[i] = ImageView(globalContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    requireContext().let {
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

        if(position == onBoardingItemAdapter.itemCount -1) {
            buttonVisibility()
        }else{
            buttonVisibility2()
        }

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

    //TODO(Function will be used to do something.)
    private fun onBoardingFinished() {
        preferences.putPrefBoolean(ONBOARDING, true)
    }

}