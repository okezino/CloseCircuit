package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.closedcircuitapplication.common.authentication.SendImage_UriToCreateAPlanInterface
import com.example.closedcircuitapplication.databinding.FragmentUploadImageBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UploadImageBottomSheetFragment(private val clickInterface: SendImage_UriToCreateAPlanInterface) : BottomSheetDialogFragment() {
    private var _binding : FragmentUploadImageBottomSheetBinding? = null
    private  val binding get() = _binding!!
    //val args : UploadImageBottomSheetFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUploadImageBottomSheetBinding.inflate(inflater, container, false)
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