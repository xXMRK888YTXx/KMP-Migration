package com.xxmrk888ytxx.preferencesstorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal open class CommonPreferencesStorage(
    private val dataStore: DataStore<Preferences>
) : PreferencesStorage {

    override suspend fun <TYPE> writeProperty(key: Preferences.Key<TYPE>, value:TYPE) {
        dataStore.edit {
            it[key] = value
        }
    }

    override fun <TYPE> getProperty(key: Preferences.Key<TYPE>, defValue:TYPE) : Flow<TYPE> {
        return dataStore.data.map {
            it[key] ?: defValue
        }
    }

    override fun <TYPE> getPropertyOrNull(key: Preferences.Key<TYPE>) : Flow<TYPE?> {
        return dataStore.data.map {
            it[key]
        }
    }

    override suspend fun <TYPE> removeProperty(key: Preferences.Key<TYPE>) {
        dataStore.edit {
            it.remove(key)
        }
    }

    override fun <TYPE> isPropertyExist(key: Preferences.Key<TYPE>) : Flow<Boolean> {
        return dataStore.data.map {
            it.contains(key)
        }
    }
}