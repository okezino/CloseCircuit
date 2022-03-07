package com.example.closedcircuitapplication.support.ui.screen

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.databinding.FragmentWhatisTheClosedCircuitBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView

class WhatIsTheClosedCircuitFragment : Fragment() {

    private var _binding: FragmentWhatisTheClosedCircuitBinding? =null
    private val binding get() = _binding!!
    private lateinit var closeCircuitVideo : StyledPlayerView
    private lateinit var  videoPlayer : SimpleExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWhatisTheClosedCircuitBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        closeCircuitVideo = binding.closedCircuitVideo
        playVideo(requireContext())
    }

    private fun playVideo(context:Context){
         videoPlayer = SimpleExoPlayer.Builder(context).build()
        val videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        closeCircuitVideo.player = videoPlayer
        val mediaItem = MediaItem.fromUri(videoUri)
        videoPlayer.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    override fun onDetach() {
        super.onDetach()
        videoPlayer.stop()
    }
}