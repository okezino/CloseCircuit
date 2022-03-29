package com.example.closedcircuitapplication.dashboard.presentation.ui.screens

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.closedcircuitapplication.authentication.utils.IMAGE_REQUEST_CODE
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.UserNameSplitterUtils
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.dashboard.domain.model.UpdateProfileRequest
import com.example.closedcircuitapplication.dashboard.presentation.ui.utils.Utils.getFileName
import com.example.closedcircuitapplication.dashboard.presentation.ui.utils.Utils.snackbar
import com.example.closedcircuitapplication.dashboard.presentation.ui.viewmodel.DashboardViewModel
import com.example.closedcircuitapplication.databinding.FragmentProfileBinding
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.PlanViewModel
import com.example.closedcircuitapplication.plan.utils.PlanConstants
import com.google.android.exoplayer2.upstream.ResolvingDataSource
import com.theartofdev.edmodo.cropper.CropImage
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : Fragment() {
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
    private lateinit var imageUrl: URL
    private lateinit var selectedImage: Uri


    @Inject
    lateinit var preferences: Preferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserDetails()
        userDetailsInitObserver()
        initObserversMyPlansTotal()

        _viewModel.getMyPlans(100, 0, "Bearer ${preferences.getToken()}")

        binding.changeProfilePic.setOnClickListener {
            if (checkPermission()) {
                requestPermission()
            } else {
                pickImage()
                uploadImage()

            }
        }

    }

    private fun uploadImage() {
        if (selectedImage == null) {
            binding.root.snackbar("Choose an image first")
            return
        }

        val parcelFileDescriptor = requireActivity().contentResolver.openAssetFileDescriptor(selectedImage, "r", null)


        var file = File(requireActivity().cacheDir, requireActivity().contentResolver.getFileName(selectedImage))
        val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
        val outPutStream = FileOutputStream(file)

        inputStream.copyTo(outPutStream)
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

                    if (mailStatus) {
                        binding.errorMessage.visibility = View.VISIBLE
                    } else {
                        binding.errorMessage.visibility = View.VISIBLE
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
                                confirmPassword
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
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                selectedImage = result.uri

                imageString = result.uri.toString()
                imageUrl = convertFileToURL(imageString)!!
                convertFileToURL(imageString)

                viewModel.updateUserProfile(
                    UpdateProfileRequest(
                        binding.profileName.text.toString(),
                        binding.profileEmail.text.toString(),
                        binding.profileNumber.text.toString(), imageUrl),
                        preferences.getUserId(), "${PlanConstants.BEARER} ${preferences.getToken()}"
                )

                setProfileImage()

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

    private fun setProfileImage() {
        try {
            val stream: InputStream? = activity?.contentResolver?.openInputStream(selectedImage)
            val bitmap: Bitmap = BitmapFactory.decodeStream(stream)
            binding.profileImageView.setImageBitmap(bitmap)

        } catch (e: Exception) {
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

}