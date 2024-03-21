package com.xxmrk888ytxx.preferencesstorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.reflect.KProperty

/**
 * [Ru]
 * Реализация [PreferencesStorage] на основе [DataStore]
 */

/**
 * [En]
 * Implementation of [PreferencesStorage] based on [DataStore]
 */

private fun createDataStore(context: Context, name: String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
        corruptionHandler = null,
        migrations = listOf(),
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    ) {
        context.preferencesDataStoreFile(name)
    }
}

private class AndroidDataStorePreferencesStorage(
    context: Context,
    name: String,
) : CommonPreferencesStorage(createDataStore(context, name))

fun PreferencesStorage.Factory.createAndroid(context: Context, name: String): PreferencesStorage =
    AndroidDataStorePreferencesStorage(context, name)