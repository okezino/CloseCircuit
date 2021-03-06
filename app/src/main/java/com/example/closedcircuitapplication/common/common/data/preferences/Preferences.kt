package com.example.closedcircuitapplication.common.common.data.preferences

import androidx.lifecycle.LiveData

interface Preferences {

    fun putToken(token: String)

    fun putPlanId(string: String)

    fun putTokenExpirationTime(time: Long)

    fun putTokenType(tokenType: String)

    fun getPlanId(): String?

    fun setDeepLinkedState(boolean: Boolean)

    fun getDeepLinkedStated() : Boolean

    fun getToken(): String

    fun getTokenExpirationTime(): Long

    fun getTokenType(): String

    fun deleteTokenInfo()

    fun putPrefBoolean(keyType: String, boolean: Boolean)

    fun saveEmail(email: String)

    fun getEmail(): String

    fun putUserId(userId: String)
    fun getUserId(): String

    fun putUserFirstName(firstName: String)
    fun getUserFirstName(): String

    fun getUserId(userIdKey: String): String

    fun putUserVerified(verifiedValue: Boolean, keyType: String)

    fun getUserVerifiedValue(key: String): Boolean

    fun putUserPhoneNumber(key: String, phoneNumber: String)

    fun getUserPhoneNumber(key: String): String

    fun putUserFullName(key: String, fullName: String)

    fun getUserFullName(key: String): LiveData<String?>

}