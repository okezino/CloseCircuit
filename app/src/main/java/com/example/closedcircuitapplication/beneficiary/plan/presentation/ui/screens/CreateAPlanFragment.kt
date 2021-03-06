package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.authentication.SendImage_UriToCreateAPlanInterface
import com.example.closedcircuitapplication.common.authentication.utils.CAMERA_REQUEST_CODE
import com.example.closedcircuitapplication.common.authentication.utils.REQUEST_CODE_IMAGE_PICKER
import com.example.closedcircuitapplication.common.authentication.utils.TO_READ_EXTERNAL_STORAGE
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.common.utils.uploadImageToFirebase
import com.example.closedcircuitapplication.databinding.FragmentCreateAPlanBinding
import com.example.closedcircuitapplication.beneficiary.plan.utils.PlanUtils


class CreateAPlanFragment : Fragment(), SendImage_UriToCreateAPlanInterface {
    private var _binding: FragmentCreateAPlanBinding? = null
    private val binding get() = _binding!!
    lateinit var sector: String
    lateinit var category: String
    private var businessType: String? = null
    private var image_data = 0
    private var uri: Uri? = null
    private var avatar: String? = null

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
        val selectPlanCategoryArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, selectplanCategory)
        binding.selectPlanCategoryDropdown.setAdapter(selectPlanCategoryArrayAdapter)

        val selectBusinessType = resources.getStringArray(R.array.business_type)
        val selectBusinessTypeArrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, selectBusinessType)
        binding.createPlanSelectBusinessTypeAutocompleteTextView.setAdapter(
            selectBusinessTypeArrayAdapter
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // show the uploadImage bottomSheet when the upload image view is clicked
        setUpAutoTextViewTextChangedListener()
        binding.uploadImageIV.setOnClickListener {
            showUploadImageBottomSheetDialog()
        }
        binding.createPlanBtn.setOnClickListener {
            sector = binding.dropdownMenu.text.toString()
            category = binding.selectPlanCategoryDropdown.text.toString()
            businessType = if (binding.createPlanSelectBusinessTypeAutocompleteTextView.visibility == View.VISIBLE) binding.createPlanSelectBusinessTypeAutocompleteTextView.text.toString() else null
            val _category = category
            val _sector = sector
            val _uri = uri.toString()

            if (_sector != null && _category != null && _uri !=null) {
                findNavController().navigate(
                    CreateAPlanFragmentDirections.actionCreateAPlanFragment2ToCreatePlanStep2Fragment(
                        sector, category, businessType,
                        avatar
                    ),
                    customNavAnimation().build()
                )
            }
        }
    }

    // show bottomsheet fragment holding the camera and uplaod image icon
    private fun showUploadImageBottomSheetDialog() {
        UploadImageBottomSheetFragment(this, "").show(
            requireActivity().supportFragmentManager,
            "uploadImageBottomSheet"
        )
    }

    // this method allow the user to pick image
    fun openImageChooser() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }

    private fun uploadImageWithCamera() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.CAMERA
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                TO_READ_EXTERNAL_STORAGE
            )
        } else {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == REQUEST_CODE_IMAGE_PICKER) {
                binding.uploadImageIV.setImageURI(data!!.data)
                uri = data.data
                uploadImageToFirebase(uri,this,requireContext()){
                    avatar = it
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        // upload image using camera
        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == CAMERA_REQUEST_CODE) {
                    val pictureBitmap = data!!.getParcelableExtra<Bitmap>("data")
                    binding.uploadImageIV.setImageBitmap(pictureBitmap)
                    val uriImage = pictureBitmap?.let { PlanUtils.getImageUriFromBitmap(requireContext(), it) }
                    uploadImageToFirebase(uriImage,this,requireContext()){
                        avatar = it
                    }
                }
            }
        }catch (e: Exception){
            e.printStackTrace()

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
                Toast.makeText(
                    context,
                    "permission was denied,please grant permission",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    //  read external storage and  get the image from the gallery
    private fun readStorage() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                TO_READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun send_ImageUri(data: Int) {
        image_data = data
        if (image_data == 1) {
            openImageChooser()
            readStorage()
        } else if (image_data == 2) {
            uploadImageWithCamera()
        }
    }

    //Function to determine when a view has been selected in any of the autoTextViews
    private fun setUpAutoTextViewTextChangedListener() {
        binding.dropdownMenu.setOnItemClickListener { parent, view, position, id ->
            enableCreatePlanButton()
        }
        binding.selectPlanCategoryDropdown.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                binding.apply {
                    createAPlanStepThreeBusinessTypeTextView.visibility = View.VISIBLE
                    createPlanStepOneSelectBusinessTypeTextInputLayout.visibility = View.VISIBLE
                    createPlanSelectBusinessTypeAutocompleteTextView.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    createAPlanStepThreeBusinessTypeTextView.visibility = View.GONE
                    createPlanStepOneSelectBusinessTypeTextInputLayout.visibility = View.GONE
                    createPlanSelectBusinessTypeAutocompleteTextView.visibility = View.GONE
                }
            }
            enableCreatePlanButton()
        }
        binding.createPlanSelectBusinessTypeAutocompleteTextView.setOnItemClickListener { parent, view, position, id ->
            enableCreatePlanButton()
        }
    }

    //Function to enable create plan button when all the autoTextView have been filled
    private fun enableCreatePlanButton() {
        if (binding.createPlanSelectBusinessTypeAutocompleteTextView.visibility == View.VISIBLE) {
            if (binding.dropdownMenu.text.isNotEmpty() && binding.selectPlanCategoryDropdown.text.isNotEmpty() && binding.createPlanSelectBusinessTypeAutocompleteTextView.text.isNotEmpty()) {
                binding.createPlanBtn.apply {
                    isEnabled = true
                    setBackgroundColor(resources.getColor(R.color.closed_circuit_dark_green))
                }
            } else {
                binding.createPlanBtn.apply {
                    isEnabled = false
                    setBackgroundColor(resources.getColor(R.color.view_disabled_background_color))
                }
            }
        } else {
            if (binding.dropdownMenu.text.isNotEmpty() && binding.selectPlanCategoryDropdown.text.isNotEmpty()) {
                binding.createPlanBtn.apply {
                    isEnabled = true
                    setBackgroundColor(resources.getColor(R.color.closed_circuit_dark_green))
                }
            } else {
                binding.createPlanBtn.apply {
                    isEnabled = false
                    setBackgroundColor(resources.getColor(R.color.view_disabled_background_color))
                }
            }
        }
    }

}