package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.ui.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.beneficiary.plan.utils.PlanConstants
import com.example.closedcircuitapplication.beneficiary.plan.utils.PlanUtils
import com.example.closedcircuitapplication.common.common.data.preferences.Preferences
import com.example.closedcircuitapplication.databinding.FragmentSponsorEmailVerificationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SponsorEmailVerificationFragment : Fragment() {

    private var _binding: FragmentSponsorEmailVerificationBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSponsorEmailVerificationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val displayEmail = PlanUtils.userEmailDisplayText(preferences.getEmail())
        "${PlanConstants.DISPLAY_TEXT_START}$displayEmail${PlanConstants.DISPLAY_TEXT_END}".also { binding.verifyEmailNotificationMessage.text = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}