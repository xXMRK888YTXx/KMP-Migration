package com.xxmrk888ytxx.securespace.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder
import com.xxmrk888ytxx.securespace.expected.ioDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FirstStartStateHolderImpl(
    private val preferencesStorage: PreferencesStorage
) : FirstStartStateHolder {

    private val stateKey = booleanPreferencesKey("FirstStartStateHolderKey")

    override val isFirstStartOfApplication: Flow<Boolean> = preferencesStorage.getProperty(stateKey,true)

    override suspend fun markFirstStartOfApplication() = withContext(ioDispatcher) {
        preferencesStorage.writeProperty(stateKey,false)
    }
}