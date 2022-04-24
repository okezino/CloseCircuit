package com.example.closedcircuitapplication.common.onboarding.presentation.view

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.closedcircuitapplication.common.common.data.preferences.Preferences
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentLinkInvitationBinding
import com.example.closedcircuitapplication.beneficiary.onboarding.utils.PLACEHOLDER_VIDEO_RESOURCE_LINK
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val TAG = "LinkInvitationFragment"
@AndroidEntryPoint
class LinkInvitationFragment : Fragment() {
    private var _binding: FragmentLinkInvitationBinding? = null
    private val binding get() = _binding!!
    private lateinit var  videoPlayer : SimpleExoPlayer
    private val arg : LinkInvitationFragmentArgs by navArgs()

    @Inject
    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLinkInvitationBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activateClickListeners()
        showPlanOwnerName()
        playVideo(requireContext())
    }

    private fun playVideo(context: Context){
        videoPlayer = SimpleExoPlayer.Builder(context).build()
        val videoUri = Uri.parse(PLACEHOLDER_VIDEO_RESOURCE_LINK)
        binding.linkInvitationExoplayer.player = videoPlayer
        val mediaItem = MediaItem.fromUri(videoUri)
        videoPlayer.apply {
            setMediaItem(mediaItem)
            prepare()
        }
    }

    private fun activateClickListeners(){
        onPlanAcceptClick()
        onPlanDeclineClick()
    }

    private fun onPlanDeclineClick(){
        binding.linkInvitationFragmentDeclineTextView.setOnClickListener {
            activity?.finishAndRemoveTask()
        }
    }

    private fun onPlanAcceptClick(){
        binding.linkInvitationFragmentAcceptTextView.setOnClickListener {
            getPlanId(arg.planId)
            findNavController().navigate(LinkInvitationFragmentDirections.actionLinkInvitationFragmentToSplashScreen(), customNavAnimation().build())
        }
    }

    private fun getPlanId(planId : String?){
        planId?.let{
            preferences.putPlanId(it)
            preferences.setDeepLinkedState(true)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun showPlanOwnerName(){
        val name = getString(R.string.placeholder_name_patience_tyav)
        val otherText = getString(R.string.someone_needs_help_text)
        val sourceString = "<b>$name</b> $otherText"
        binding.linkInvitationFragmentPersonNeedsHelpTextView.setText(Html.fromHtml(sourceString, 0))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}