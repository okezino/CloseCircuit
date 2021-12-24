package com.example.closedcircuitapplication.ui.createAPlantScreenUi

import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.authentication.REQUEST_CODE_IMAGE_PICKER
import com.example.closedcircuitapplication.authentication.TO_READ_EXTERNAL_STORAGE
import com.example.closedcircuitapplication.databinding.FragmentCreateAPlanBinding


class CreateAPlanFragment : Fragment() {
    private var _binding: FragmentCreateAPlanBinding? = null
    private val binding get() = _binding!!
    lateinit var uri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateAPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val planCategory = resources.getStringArray(R.array.support_means)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, planCategory)
        binding.dropdownMenu.setAdapter(arrayAdapter)

        val selectplanCategory = resources.getStringArray(R.array.selectPlanCategory)
        val selectPlanCategoryArrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, selectplanCategory)
        binding.selectPlanCategoryDropdown.setAdapter(selectPlanCategoryArrayAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uploadImageIV.setOnClickListener {
            binding.planImageCard.visibility = View.VISIBLE

        }
        binding.galaryCardView.setOnClickListener {
            openImageChooser()
            binding.planImageCard.visibility = View.GONE
            readStorage()
        }

        binding.cameraCardView.setOnClickListener {
            openImageChooser()
            binding.planImageCard.visibility = View.GONE
            readStorage()
        }


    }
    // this method allow the user to pick image
    fun openImageChooser(){
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }

    private fun readStorage(){
        if (ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                TO_READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null){
             uri = data.data!!

        }
        binding.uploadImageIV.setImageURI(uri)
    }
}