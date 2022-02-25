package com.example.closedcircuitapplication.common.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.KEY_EMAIL
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.KEY_TOKEN
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.KEY_TOKEN_EXPIRATION_TIME
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.KEY_TOKEN_TYPE
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.USER_ID
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants.USER_FIRST_NAME
import javax.inject.Inject

class ClosedCircuitPreferences @Inject constructor(
    context: Context
) : Preferences {

    companion object {
        const val PREFERENCES_NAME = "CLOSED_CIRCUIT_PREFERENCES"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun putToken(token: String) {
        edit { putString(KEY_TOKEN, token) }
    }

    override fun putTokenExpirationTime(time: Long) {
        edit { putLong(KEY_TOKEN_EXPIRATION_TIME, time) }
    }

    override fun putTokenType(tokenType: String) {
        edit { putString(KEY_TOKEN_TYPE, tokenType) }
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }

    override fun getToken(): String {
        return preferences.getString(KEY_TOKEN, "").orEmpty()
    }

    override fun getTokenExpirationTime(): Long {
        return preferences.getLong(KEY_TOKEN_EXPIRATION_TIME, -1)
    }

    override fun getTokenType(): String {
        return preferences.getString(KEY_TOKEN_TYPE, "").orEmpty()
    }

    override fun deleteTokenInfo() {
        edit {
            remove(KEY_TOKEN)
            remove(KEY_TOKEN_EXPIRATION_TIME)
            remove(KEY_TOKEN_TYPE)
        }
    }

    override fun putPrefBoolean(keyType: String, boolean: Boolean) {
        edit { putBoolean(keyType, boolean) }
    }

    override fun saveEmail(email: String) {
        edit { putString(KEY_EMAIL, email) }
    }

    override fun getEmail(): String {
        return preferences.getString(KEY_EMAIL, "").orEmpty()
    }

    override fun putUserId(userId: String) {
        edit { putString(USER_ID, userId) }
    }

    override fun getUserId(): String {
        return preferences.getString(USER_ID, "").orEmpty()
    }
    override fun putUserVerified(verifiedValue: Boolean, keyType: String) {
        edit {
            putBoolean(keyType, verifiedValue)
        }
    }

    override fun getUserVerifiedValue(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    override fun putUserFirstName(firstName: String) {
        edit { putString(USER_FIRST_NAME, firstName) }
    }

    override fun getUserFirstName(): String {
        return preferences.getString(USER_FIRST_NAME,"").orEmpty()
    }


}