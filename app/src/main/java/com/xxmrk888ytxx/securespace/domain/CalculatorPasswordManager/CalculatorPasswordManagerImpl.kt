package com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager

import androidx.datastore.preferences.core.stringPreferencesKey
import com.xxmrk888ytxx.cryptomanager.AndroidCryptoManager
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.text.Charsets.UTF_8

class CalculatorPasswordManagerImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
    private val androidCryptoManager: AndroidCryptoManager
) : CalculatorPasswordManager {

    private val preferencesKey = stringPreferencesKey("calculatorPasswordHash")

    private suspend fun calculateHash(string: String) : String {
        return androidCryptoManager.hashFromData(string.toByteArray(UTF_8))
    }

    override suspend fun setupPassword(password: String) = withContext(Dispatchers.IO) {
        if(isPasswordSetup()) throw IllegalStateException("Calculator password already setup")

        preferencesStorage.writeProperty(preferencesKey,calculateHash(password))
    }

    override suspend fun checkPassword(checkingPassword: String): Boolean = withContext(Dispatchers.IO) {
        val currentPassword = preferencesStorage.getPropertyOrNull(preferencesKey).first() ?: throw IllegalStateException("Calculator password not setup")

        return@withContext calculateHash(checkingPassword) == currentPassword
    }

    override suspend fun isPasswordSetup(): Boolean = withContext(Dispatchers.IO) {
        return@withContext preferencesStorage.isPropertyExist(preferencesKey).first()
    }


}