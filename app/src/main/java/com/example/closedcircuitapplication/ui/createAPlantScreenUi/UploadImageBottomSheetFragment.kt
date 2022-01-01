package com.example.closedcircuitapplication.ui.createAPlantScreenUi

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.CAMERA_REQUEST_CODE
import com.example.closedcircuitapplication.authentication.REQUEST_CODE_IMAGE_PICKER
import com.example.closedcircuitapplication.authentication.TO_READ_EXTERNAL_STORAGE
import com.example.closedcircuitapplication.databinding.FragmentUploadImageBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UploadImageBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentUploadImageBottomSheetBinding? = null
    private  val binding get() = _binding!!
    lateinit var image_uri: String


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
            openImageChooser()
            readStorage()
        }

        binding.cameraCardView.setOnClickListener {
            uploadImageWithCamera()
        }
    }

    // this method allow the user to pick image
    fun openImageChooser() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }



    fun uploadImageWithCamera() {
        if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.CAMERA) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                TO_READ_EXTERNAL_STORAGE
            )
        } else {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            image_uri = data.data!!.toString()

        }
            if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                 image_uri = data!!.data!!.toString()


               // findNavController().navigate(action)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            } else {
                Toast.makeText(context, "permission was denied,please grant permission", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun readStorage() {
        if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                TO_READ_EXTERNAL_STORAGE
            )
        }
    }
}