package com.example.closedcircuitapplication.ui.onboardingscreen

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
import androidx.fragment.app.FragmentTransaction
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentViewPagerBinding
import com.example.closedcircuitapplication.ui.onboardingscreen.adapter.OnBoardingItemAdapter
import com.example.closedcircuitapplication.ui.onboardingscreen.adapter.ViewPagerAdapter
import com.example.closedcircuitapplication.ui.onboardingscreen.screens.FirstScreenFragment
import com.example.closedcircuitapplication.ui.onboardingscreen.screens.FourthScreenFragment
import com.example.closedcircuitapplication.ui.onboardingscreen.screens.SecondScreenFragment
import com.example.closedcircuitapplication.ui.onboardingscreen.screens.ThirdScreenFragment

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    private var _binding : FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var onBoardingItemAdapter: OnBoardingItemAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(layoutInflater,container,false)

        onBoardingItemAdapter = OnBoardingItemAdapter()

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
        var fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction();

//        Fragment fraggy = new DummyFragment();
//        fragmentTransaction.add(R.id.fragment_container, fraggy);
//        fragmentTransaction.commit();

        viewPagerAdapter = ViewPagerAdapter(fragmentList, fragmentManager , lifecycle)

        binding.onBoardingViewPager.adapter = adapter
        setUpIndicator()

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setUpOnBoardingItem()
//        setUpIndicator()
//        setUpCurrentIndicator(0)
//    }

//    private fun setUpCurrentIndicator(i: Int) {
//
//    }

//    private fun setUpOnBoardingItem() {
//        TODO("Not yet implemented")
//    }
//
//    private fun onBoardingFinished() {
//        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
//        val editor = sharedPref.edit()
//        editor.putBoolean("finished",true)
//        editor.apply()
//    }
//
//    private fun navigateToLoginFragment() {
//        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        onBoardingFinished()
//    }

//    private fun setUpCurrentIndicator(i: Int) {
//
//    }

    private fun setUpIndicator() {
        val indicators = arrayOfNulls<ImageView>(onBoardingItemAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(requireContext())
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorController.addView(indicators[i])
        }
    }

//    private fun setUpOnBoardingItem() {
//        onBoardingItemAdapter = OnBoardingItemAdapter(
//            listOf(
//                AdapterModel(
//                    onBoardingImage = R.drawable.ic_idea_illustration,
//                    title = "Easily raise funds for your business or projects",
//                    description = "Start a business or project and get your family, friends or wellwishers to financially support from anywhere in the world."
//                ),
//                AdapterModel(
//                    onBoardingImage = R.drawable.ic_project_illustration,
//                    title = "Get guided execution. Create a budget and action steps",
//                    description = "Follow the guided steps on execution of your business or project to unlock funds and stay accountable to your sponsors."
//                ),
//                AdapterModel(
//                    onBoardingImage = R.drawable.ic_sponsor_illustration,
//                    title = "Support, incubate and mentor with your financial contributions",
//                    description = "Ensure your financial contributions are meaningfully used to establish success in the business or project funded."
//                ),
//                AdapterModel(
//                    onBoardingImage = R.drawable.ic_investment_illustration,
//                    title = "Discover diligently executed ideas to invest in",
//                    description = "Discover investment opportunities in star performing businesses or projects on the platform."
//                )
//            )
//        )

//        binding.onboardingViewPager.adapter = onBoardingItemAdapter
//        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
////                setUpCurrentIndicator(position)
//            }
//        })
//        (binding.onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
//            RecyclerView.OVER_SCROLL_NEVER
//        val onBoardingVp = binding.onboardingViewPager

//        binding.nextBtn.setOnClickListener {
//            if (onBoardingVp.currentItem +1 < onBoardingItemAdapter.itemCount){
//                onBoardingVp.currentItem +=1
//
//            }else{
//
//                navigateToLoginFragment()
//                onBoardingFinished()
//            }
//        }
//        binding.skipBtn.setOnClickListener {
//            navigateToLoginFragment()
//            onBoardingFinished()
//        }
//    }

}