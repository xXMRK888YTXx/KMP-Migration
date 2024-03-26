package com.xxmrk888ytxx.securespace.data

import androidx.datastore.preferences.core.stringPreferencesKey
import com.xxmrk888ytxx.cryptomanager.CryptoManager
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager
import com.xxmrk888ytxx.securespace.expected.ioDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class CalculatorPasswordManagerImpl(
    private val preferencesStorage: PreferencesStorage,
    private val androidCryptoManager: CryptoManager
) : CalculatorPasswordManager {

    private val preferencesKey = stringPreferencesKey("calculatorPasswordHash")

    private suspend fun calculateHash(string: String) : String {
        return androidCryptoManager.hashFromData(string.encodeToByteArray())
    }

    override suspend fun setupPassword(password: String) = withContext(ioDispatcher) {
        if(isPasswordSetup()) throw IllegalStateException("Calculator password already setup")

        preferencesStorage.writeProperty(preferencesKey,calculateHash(password))
    }

    override suspend fun checkPassword(checkingPassword: String): Boolean = withContext(ioDispatcher) {
        val currentPassword = preferencesStorage.getPropertyOrNull(preferencesKey).first() ?: throw IllegalStateException("Calculator password not setup")

        return@withContext calculateHash(checkingPassword) == currentPassword
    }

    override suspend fun isPasswordSetup(): Boolean = withContext(ioDispatcher) {
        return@withContext preferencesStorage.isPropertyExist(preferencesKey).first()
    }


}