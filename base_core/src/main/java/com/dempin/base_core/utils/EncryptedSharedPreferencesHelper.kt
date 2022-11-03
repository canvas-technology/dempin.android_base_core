package com.dempin.base_core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class EncryptedSharedPreferencesHelper(context: Context) {

    private val keyGenParameterSpec = KeyGenParameterSpec.Builder(
        MasterKey.DEFAULT_MASTER_KEY_ALIAS,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setRandomizedEncryptionRequired(false)
        .setKeySize(KEY_SIZE)
        .build()

    private val masterKey = MasterKey.Builder(context)
        .setKeyGenParameterSpec(keyGenParameterSpec)
        .build()

    private val encryptedSharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )


    fun removeString(key: String) {
        val editor = encryptedSharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun put(key: String, value: String) {
        val editor = encryptedSharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun put(key: String, value: Boolean) {
        val editor = encryptedSharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun put(key: String, value: Int) {
        val editor = encryptedSharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun put(key: String, value: Double) {
        val editor = encryptedSharedPreferences.edit()
        editor.putFloat (key, value.toFloat())
        editor.apply()
    }

    fun getString(key: String, defaultValue: String = ""): String =
        encryptedSharedPreferences.getString(key, defaultValue).toString()

    fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        encryptedSharedPreferences.getBoolean(key, defaultValue)

    fun getInt(key: String, defaultValue: Int): Int =
        encryptedSharedPreferences.getInt(key, defaultValue)

    fun getDouble(key: String, defaultValue: Double = 0.0): Double =
        encryptedSharedPreferences.getFloat(key, defaultValue.toFloat()).toDouble()

    @SuppressLint("CommitPrefEdits")
    fun clear(){
        val editor = encryptedSharedPreferences.edit()
        editor.clear()
    }

    companion object {
        private const val KEY_SIZE: Int = 256
        private const val FILE_NAME: String = "secret_shared_prefs"
    }
}
