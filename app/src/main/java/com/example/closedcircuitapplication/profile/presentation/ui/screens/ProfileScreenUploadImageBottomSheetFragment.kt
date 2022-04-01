package com.example.closedcircuitapplication.profile.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.SendImage_UriToCreateAPlanInterface
import com.example.closedcircuitapplication.databinding.FragmentProfileScreenUploadImageBottomSheetBinding
import com.example.closedcircuitapplication.databinding.FragmentUploadImageBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProfileScreenUploadImageBottomSheetFragment(private val clickInterface: SendImage_UriToCreateAPlanInterface) : BottomSheetDialogFragment() {

    private var _binding : FragmentProfileScreenUploadImageBottomSheetBinding? = null
    private  val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileScreenUploadImageBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.galaryCardView.setOnClickListener {
            passGalaryDataToCreateAPlanFragment()
        }
        binding.galleryTv.setOnClickListener {
            passGalaryDataToCreateAPlanFragment()
        }

        binding.cameraCardView.setOnClickListener {
            passCameraDataToCreateAPlanFragment()
        }
        binding.cameraTv.setOnClickListener {
            passCameraDataToCreateAPlanFragment()
        }
    }
    fun passGalaryDataToCreateAPlanFragment(){
        clickInterface.send_ImageUri(1)
        dismiss()
    }
    fun passCameraDataToCreateAPlanFragment(){
        clickInterface.send_ImageUri(2)
        dismiss()
    }
    }

