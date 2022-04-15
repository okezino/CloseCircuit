package com.example.closedcircuitapplication.beneficiary.onboarding.presentation.ui.screens

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentLinkInvitationBinding
import com.example.closedcircuitapplication.beneficiary.onboarding.utils.PLACEHOLDER_VIDEO_RESOURCE_LINK
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView


class LinkInvitationFragment : Fragment() {
    private var _binding: FragmentLinkInvitationBinding? = null
    private val binding get() = _binding!!
    private lateinit var closeCircuitVideo : PlayerView
    private lateinit var  videoPlayer : SimpleExoPlayer

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
        binding.linkInvitationFragmentAcceptTextView.setOnClickListener {
            findNavController().navigate(LinkInvitationFragmentDirections.actionLinkInvitationFragmentToSplashScreen(), customNavAnimation().build())
        }

        binding.linkInvitationFragmentDeclineTextView.setOnClickListener {
            activity?.finishAndRemoveTask()
        }

        val name = getString(R.string.placeholder_name_patience_tyav)
        val otherText = getString(R.string.someone_needs_help_text)

        val sourceString = "<b>$name</b> $otherText"
        binding.linkInvitationFragmentPersonNeedsHelpTextView.setText(Html.fromHtml(sourceString, 0))

        closeCircuitVideo = binding.linkInvitationExoplayer
        playVideo(requireContext())
    }

    private fun playVideo(context: Context){
        videoPlayer = SimpleExoPlayer.Builder(context).build()
        val videoUri = Uri.parse(PLACEHOLDER_VIDEO_RESOURCE_LINK)
        closeCircuitVideo.player = videoPlayer
        val mediaItem = MediaItem.fromUri(videoUri)
        videoPlayer.apply {
            setMediaItem(mediaItem)
            prepare()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}