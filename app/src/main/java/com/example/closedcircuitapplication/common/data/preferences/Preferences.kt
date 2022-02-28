package com.example.closedcircuitapplication.common.data.preferences

interface Preferences {

    fun putToken(token: String)

    fun putTokenExpirationTime(time: Long)

    fun putTokenType(tokenType: String)

    fun getToken(): String

    fun getTokenExpirationTime(): Long

    fun getTokenType(): String

    fun deleteTokenInfo()

    fun putPrefBoolean(keyType: String, boolean: Boolean)

    fun saveEmail(email: String)

    fun getEmail(): String

    fun putUserId(userId: String)
    fun getUserId(): String

    fun putUserFirstName(firstName:String)
    fun getUserFirstName(): String

    fun getUserId(userIdKey: String): String

    fun putUserVerified(verifiedValue: Boolean, keyType: String)

    fun getUserVerifiedValue(key: String): Boolean

    fun putUserPhoneNumber(key: String, phoneNumber: String)

    fun getUserPhoneNumber(key: String): String

}