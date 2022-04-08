package com.example.closedcircuitapplication.common.utils

import android.content.Context
import android.net.Uri
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.plan.utils.PlanConstants
import com.google.firebase.storage.FirebaseStorage
import java.util.*

//FIREBASE IMAGE UPLOAD
fun uploadImageToFirebase(fileUri: Uri?, fragment: Fragment, context: Context, getUrl: (string: String)-> Unit){

    if (fileUri != null) {
        val fileName = UUID.randomUUID().toString()
        val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")
        val dialog = fragment.setProgressDialog(context, PlanConstants.UPLOAD_MESSAGE)
        dialog.show()
        refStorage.putFile(fileUri)
            .addOnSuccessListener { taskSnapshot ->
                val result = taskSnapshot.metadata!!.reference!!.downloadUrl
                result.addOnSuccessListener { imageUrl->
                    getUrl(imageUrl.toString())
                    dialog.dismiss()
                    fragment.makeSnackBar(PlanConstants.IMAGE_UPLOAD, fragment.requireView())
                }
            }
            .addOnFailureListener { e ->
                fragment.makeSnackBar("${e.message}", fragment.requireView())
                dialog.dismiss()
            }
    }
}