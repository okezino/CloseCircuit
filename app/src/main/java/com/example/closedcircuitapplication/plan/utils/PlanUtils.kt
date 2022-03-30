package com.example.closedcircuitapplication.plan.utils


import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.example.closedcircuitapplication.common.utils.DEFAULT_IMAGE
import java.io.ByteArrayOutputStream

object PlanUtils {
    fun userEmailDisplayText(email: String): String {
        var displayEmail = ""
        val userEmailSplit = email.split("@")
        val userEmailEnd = userEmailSplit[1]
        var userSubEmail: String
        if (userEmailSplit[0].length >= 7){
             userSubEmail = email.substring(0, 3)
            displayEmail = "$userSubEmail****@$userEmailEnd"
        }
        if (userEmailSplit[0].length < 7){
             userSubEmail = email.substring(0, 2)
            displayEmail = "$userSubEmail****@$userEmailEnd"
        }
        if (userEmailSplit[0].length in 5..5){
             userSubEmail = email[0].toString()
            displayEmail = "$userSubEmail****@$userEmailEnd"

        }
        if (userEmailSplit[0].length in 4..4){
            userSubEmail = email[0].toString()
            displayEmail = "$userSubEmail***@$userEmailEnd"
        }
        if (userEmailSplit[0].length in 3..3){
             userSubEmail = email[0].toString()
            displayEmail = "$userSubEmail**@$userEmailEnd"
        }
        if (userEmailSplit[0].length in 2..2){
            userSubEmail = email[0].toString()
            displayEmail = "$userSubEmail*@$userEmailEnd"
        }
        return displayEmail
    }

    fun planDescriptionMaxWords(description: String): Int {
        val maxWords: String = description.replace("\n", " ")
        val descriptionText: List<String> = maxWords.split(" ")
        return descriptionText.size
    }
    fun avatarUpload(avatar: String?): String{
        var avatarValue = ""
        avatarValue = if (avatar == null || !avatar.startsWith("http")){ DEFAULT_IMAGE }else{ avatar }
        return avatarValue
    }
    fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }
}