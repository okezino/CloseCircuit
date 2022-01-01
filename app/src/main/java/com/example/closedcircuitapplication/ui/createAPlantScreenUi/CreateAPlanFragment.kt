package com.example.closedcircuitapplication.ui.createAPlantScreenUi

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.CAMERA_REQUEST_CODE
import com.example.closedcircuitapplication.authentication.REQUEST_CODE_IMAGE_PICKER
import com.example.closedcircuitapplication.authentication.TO_READ_EXTERNAL_STORAGE
import com.example.closedcircuitapplication.databinding.FragmentCreateAPlanBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateAPlanFragment : Fragment() {
    private var _binding: FragmentCreateAPlanBinding? = null
    private val binding get() = _binding!!
    lateinit var uri: Uri
    lateinit var sector: String
    lateinit var category: String

  // private val args: CreateAPlanFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateAPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val planCategory = resources.getStringArray(R.array.Choose_business_Sector)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, planCategory)
        binding.dropdownMenu.setAdapter(arrayAdapter)

        val selectplanCategory = resources.getStringArray(R.array.selectPlanCategory)
        val selectPlanCategoryArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, selectplanCategory)
        binding.selectPlanCategoryDropdown.setAdapter(selectPlanCategoryArrayAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uploadImageIV.setOnClickListener {
         //   binding.planImageCard.visibility = View.VISIBLE
            showUploadImageBottomSheetDialog()

          //  binding.planImageCard.visibility = View.VISIBLE
        }

        binding.createPlanBtn.setOnClickListener {
            sector = binding.selectPlanCategoryDropdown.text.toString()
            category = binding.dropdownMenu.text.toString()

            Log.d("TEST", "SECTOR=====> $sector and CATEGORY====> $category ")
           //val action = CreateAPlanFragmentDirections.(category, sector)
         //  findNavController().navigate(action)
        }

       // binding.uploadImageIV.setImageURI(uri)
        //binding.uploadImageIV.setImageURI(thumbNail)

    }

    private fun showUploadImageBottomSheetDialog() {
        findNavController().navigate(R.id.action_createAPlanFragment2_to_uploadImageBottomSheetFragment2)

    }



}