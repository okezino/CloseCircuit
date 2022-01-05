package com.example.closedcircuitapplication.ui.createAPlantScreenUi

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.CAMERA_REQUEST_CODE
import com.example.closedcircuitapplication.authentication.REQUEST_CODE_IMAGE_PICKER
import com.example.closedcircuitapplication.authentication.SendImage_UriToCreateAPlanFragment
import com.example.closedcircuitapplication.authentication.TO_READ_EXTERNAL_STORAGE
import com.example.closedcircuitapplication.databinding.FragmentUploadImageBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UploadImageBottomSheetFragment(private val clickInterface : SendImage_UriToCreateAPlanFragment) : BottomSheetDialogFragment() {
    //var clickInterface : SendImage_UriToCreateAPlanFragment? = null
    private var _binding : FragmentUploadImageBottomSheetBinding? = null
    private  val binding get() = _binding!!
    val args : UploadImageBottomSheetFragmentArgs by navArgs()


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
            val action = UploadImageBottomSheetFragmentDirections.actionUploadImageBottomSheetFragment2ToCreateAPlanFragment2(1)
            findNavController().navigate(action)
        }

        binding.cameraCardView.setOnClickListener {
            val action = UploadImageBottomSheetFragmentDirections.actionUploadImageBottomSheetFragment2ToCreateAPlanFragment2(2)
            findNavController().navigate(action)
        }

    }


}