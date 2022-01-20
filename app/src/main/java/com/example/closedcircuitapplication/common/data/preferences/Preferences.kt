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

}