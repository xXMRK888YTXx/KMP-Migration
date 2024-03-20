package com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirstStartStateHolderImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage
) : FirstStartStateHolder {

    private val stateKey = booleanPreferencesKey("FirstStartStateHolderKey")

    override val isFirstStartOfApplication: Flow<Boolean> = preferencesStorage.getProperty(stateKey,true)

    override suspend fun markFirstStartOfApplication() = withContext(Dispatchers.IO) {
        preferencesStorage.writeProperty(stateKey,false)
    }
}