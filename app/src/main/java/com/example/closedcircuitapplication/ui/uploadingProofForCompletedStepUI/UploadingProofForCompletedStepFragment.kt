package com.example.closedcircuitapplication.ui.uploadingProofForCompletedStepUI

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.REQUEST_CODE_IMAGE_PICKER
import com.example.closedcircuitapplication.authentication.TO_READ_EXTERNAL_STORAGE
import com.example.closedcircuitapplication.databinding.FragmentUploadingProofForCompletedStepBinding


class UploadingProofForCompletedStepFragment : Fragment() {
    private var _binding : FragmentUploadingProofForCompletedStepBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        val selectBudget = resources.getStringArray(R.array.Select_Budget)
        val arrayAdapter = ArrayAdapter(requireActivity(), R.layout.dropdown_item, selectBudget)
        binding.selectBudgetDropdown.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUploadingProofForCompletedStepBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveAndAddNewProofBtn.setOnClickListener {
            findNavController().navigate(R.id.requestVerificationSuccessfullFragment)
        }
        binding.uploadimageInputlayout.setStartIconOnClickListener {
            openImageChooser()
            readStorage()
        }
    }

    // this method allow the user to pick image
    fun openImageChooser() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }

    //  read external storage and  get the image from the galery
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.uploadsTV.visibility = View.VISIBLE
        binding.uploadsInputlayout.visibility = View.VISIBLE
        binding.uploadsInputlayout.setText(data!!.data!!.encodedPath)
    }
}