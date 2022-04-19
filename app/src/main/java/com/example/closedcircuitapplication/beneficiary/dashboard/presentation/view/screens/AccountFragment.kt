package com.example.closedcircuitapplication.beneficiary.dashboard.presentation.view.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.common.utils.handleBackPress
import com.example.closedcircuitapplication.common.common.utils.popBackStack
import com.example.closedcircuitapplication.databinding.FragmentAccountBinding
import com.google.android.material.tabs.TabLayout

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleBackPress()
        setUpCustomTabLayout()
        binding.fragmentUserAccountBackArrowIv.setOnClickListener { popBackStack() }

        binding.navigateToLoanScreen.setOnClickListener {
            findNavController().navigate(
               AccountFragmentDirections.actionAccountFragment2ToLoansScreenFragment2(),
                customNavAnimation().build()
            )
        }
    }

    private fun setUpCustomTabLayout(){
        tabLayout = binding.fragmentUserAccountTabLayout
        val tabInflater = (layoutInflater).context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customTab = tabInflater.inflate(R.layout.custom_tabs,null,false)
        val fundsReleasedTabItem = customTab.findViewById(R.id.fragment_user_account_funds_released_tabItem) as LinearLayout
        val fundsWithheldTabItem = customTab.findViewById(R.id.fragment_user_account_funds_withheld_tabItem) as LinearLayout
        val pendingRequestTabItem = customTab.findViewById(R.id.fragment_user_account_pending_funds_tabItem) as LinearLayout

        with(binding) {
            fragmentUserAccountTabLayout.addTab(fragmentUserAccountTabLayout.newTab().setCustomView(fundsReleasedTabItem))
            fragmentUserAccountTabLayout.addTab(fragmentUserAccountTabLayout.newTab().setCustomView(fundsWithheldTabItem))
            fragmentUserAccountTabLayout.addTab(fragmentUserAccountTabLayout.newTab().setCustomView(pendingRequestTabItem))
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
