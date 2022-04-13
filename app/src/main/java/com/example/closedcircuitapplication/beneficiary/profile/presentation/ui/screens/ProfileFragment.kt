package com.example.closedcircuitapplication.beneficiary.profile.presentation.ui.screens

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.closedcircuitapplication.common.authentication.SendImage_UriToCreateAPlanInterface
import com.example.closedcircuitapplication.common.authentication.utils.CAMERA_REQUEST_CODE
import com.example.closedcircuitapplication.common.authentication.utils.IMAGE_REQUEST_CODE
import com.example.closedcircuitapplication.common.authentication.utils.REQUEST_CODE_IMAGE_PICKER
import com.example.closedcircuitapplication.common.authentication.utils.TO_READ_EXTERNAL_STORAGE
import com.example.closedcircuitapplication.common.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.common.data.preferences.PreferencesConstants
import com.example.closedcircuitapplication.common.common.utils.*
import com.example.closedcircuitapplication.beneficiary.dashboard.domain.model.UpdateProfileRequest
import com.example.closedcircuitapplication.beneficiary.dashboard.presentation.ui.viewmodel.DashboardViewModel
import com.example.closedcircuitapplication.databinding.FragmentProfileBinding
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels.PlanViewModel
import com.example.closedcircuitapplication.beneficiary.plan.utils.PlanConstants
import com.example.closedcircuitapplication.beneficiary.plan.utils.PlanUtils
import com.google.firebase.auth.FirebaseAuth
import com.theartofdev.edmodo.cropper.CropImage
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.net.MalformedURLException
import java.net.URL
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : Fragment(), SendImage_UriToCreateAPlanInterface {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()
    private val _viewModel: PlanViewModel by viewModels()

    private lateinit var fullName: String
    private lateinit var phoneNumber: String
    private lateinit var nationality: String
    private lateinit var userId: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmPassword: String
    private var mailStatus: Boolean = false
    private lateinit var imageString: String
    private lateinit var avatar1: String
    private lateinit var avatar2: String
    private var selectedImage: Uri? = null
    private var image_data = 0

    var mAuth = FirebaseAuth.getInstance()


    @Inject
    lateinit var preferences: Preferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserDetails()
        userDetailsInitObserver()
        initObserversMyPlansTotal()

        handleBackPress()
        binding.fragmentProfileScreenToolbarBackArrowIv.setOnClickListener { popBackStack() }

        _viewModel.getMyPlans(100, 0, "Bearer ${preferences.getToken()}")

        binding.changeProfilePic.setOnClickListener {
            if (checkPermission()) {
                requestPermission()
            } else {
                showUploadImageBottomSheetDialog()
            }
        }
    }

    private fun showUploadImageBottomSheetDialog() {
        ProfileScreenUploadImageBottomSheetFragment(this).show(
            requireActivity().supportFragmentManager,
            "uploadImageBottomSheet"
        )
    }

    override fun onStart() {
        super.onStart()
        val user = mAuth.currentUser
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously()
        }
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
            startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }

    private fun signInAnonymously() {
        mAuth.signInAnonymously().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = mAuth.currentUser
            }
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

    private fun checkPermission(): Boolean {
        val result1: Boolean = ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
        val result2: Boolean = ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED
        return result1 && result2
    }


    private fun requestPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
            IMAGE_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            IMAGE_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "PERMISSION DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

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

    private fun picImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }


    private fun userDetailsInitObserver() {
        viewModel.userDetailsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    fullName = it.data.data?.fullName.toString()
                    userId = it.data.data?.id.toString()
                    val firstName = UserNameSplitterUtils.userFullName(fullName)
                    binding.profileName.text = fullName
                    binding.profileEmail.text = it.data.data?.email
                    Glide.with(this)
                        .load(Uri.parse(it.data.data?.avatar))
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.profileImageView)
                    phoneNumber = it.data.data?.phoneNumber.toString()
                    nationality = it.data.data?.country.toString()
                    email = it.data.data?.email.toString()
                    password = it.data.data?.password.toString()
                    confirmPassword = it.data.data?.password.toString()
                    binding.profileNumber.text = it.data.data?.phoneNumber
                    binding.profileNationality.text = it.data.data?.country
                    mailStatus = it.data.data?.isVerified!! == true
                    avatar1 = it.data.data.avatar

                    if (mailStatus) {
                        binding.errorMessage.visibility = View.INVISIBLE
                    } else {
                        binding.errorMessage.visibility = View.INVISIBLE
                    }

                    binding.profileEditButton.setOnClickListener {

                        findNavController().navigate(
                                ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(
                                fullName,
                                phoneNumber,
                                nationality,
                                userId,
                                email,
                                password,
                                confirmPassword,
                                avatar1
                            ),
                            customNavAnimation().build()
                        )
                    }
//                  saving email to sharedPreference
                    it.data.data.let { email -> saveEmail(email.email) }
//                  save verification status to sharedPreference
                    it.data.data.isVerified?.let { status -> saveEmailStatus(status) }
                    // save user first name to sharedPreference
                }
                is Resource.Error -> {
                    makeSnackBar("${it.data?.message}", requireView())
                }
            }
        }
    }

    private fun pickImage() {
        CropImage.activity()
            .start(requireContext(), this)

    }

    @Throws(MalformedURLException::class)
    fun convertFileToURL(filePath: String): URL? {
        val file = File(filePath.trim { it <= ' ' })
        return file.toURI().toURL()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == REQUEST_CODE_IMAGE_PICKER) {
                binding.profileImageView.setImageURI(data!!.data)
                selectedImage = data.data
                uploadImageToFirebase(selectedImage, this, requireContext()) {
                    avatar2 = it
                    Log.d("myUri", avatar2)
                    viewModel.updateUserProfile(
                        UpdateProfileRequest(fullName, email, phoneNumber, "${avatar2}.jpeg"),
                        preferences.getUserId(), "${PlanConstants.BEARER} ${preferences.getToken()}"
                    )
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
                    binding.profileImageView.setImageBitmap(pictureBitmap)
                    val uriImage =
                        pictureBitmap?.let { PlanUtils.getImageUriFromBitmap(requireContext(), it) }
                    uploadImageToFirebase(uriImage, this, requireContext()) {
                        avatar2 = it
                        Log.d("myUri", avatar2)
                        viewModel.updateUserProfile(
                            UpdateProfileRequest(fullName, email, phoneNumber, "${avatar2}.jpeg"),
                            preferences.getUserId(), "${PlanConstants.BEARER} ${preferences.getToken()}"
                        )

                    }
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun initObserversMyPlansTotal() {
        _viewModel.getMyPlansResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    makeSnackBar("Loading...", requireView())
                }
                is Resource.Success -> {
                    resource.data.data?.plans
                    binding.plansTotal.text = resource.data.data?.plans?.size.toString()
                }
                is Resource.Error -> {
                    makeSnackBar("${resource.data?.message}", requireView())
                }
            }
        }
    }

    private fun getUserDetails() {
        viewModel.getUserDetails(preferences.getUserId(), "Bearer ${preferences.getToken()}")
    }

    private fun saveEmail(email: String) = preferences.saveEmail(email)
    private fun saveEmailStatus(status: Boolean) =
        preferences.putUserVerified(status, PreferencesConstants.USER_VERIFIED)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

}