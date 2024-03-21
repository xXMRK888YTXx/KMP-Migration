package com.xxmrk888ytxx.preferencesstorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import java.io.File

/**
 * [Ru]
 * Класс для управления пользовательскими предпочтениями
 */

/**
 * [En]
 * Class for managing user preferences
 */
interface PreferencesStorage {
    /**
     * [Ru]
     * Записывает значение по ключу
     *
     * @param key - Ключ по которому будет записано заначение
     * @param value - Значение которое будет записано
     */

    /**
     * [En]
     * Writes value by key
     *
     * @param key - The key by which the value will be written
     * @param value - Value to be written
     */
    suspend fun <TYPE> writeProperty(key: Preferences.Key<TYPE>, value: TYPE)

    /**
     * [Ru]
     * Получает значение по ключу
     *
     * @param key - Ключ по которому будет записано заначение
     * @param defValue - Стандартное значение, которое будет возвращено если,
     * значение по ключу не будет найдено
     */

    /**
     * [En]
     * Gets value by key
     *
     * @param key - The key by which the value will be written
     * @param defValue - Default value that will be returned if,
     * key value will not be found
     */
    fun <TYPE> getProperty(key: Preferences.Key<TYPE>, defValue: TYPE): Flow<TYPE>

    /**
     * [Ru]
     * Возвращает значение по ключу, если значение не будет найдено возвращает null
     *
     * @param key - Ключ по которому будет записано заначение
     */

    /**
     * [En]
     * Returns a value by key, if the value is not found returns null
     *
     * @param key - The key by which the value will be written
     */
    fun <TYPE> getPropertyOrNull(key: Preferences.Key<TYPE>): Flow<TYPE?>

    /**
     * [Ru]
     * Удаляет значение по ключу.
     *
     * @param key - Ключ, по которому будут удалены данные.
     */

    /**
     * [En]
     * Removes a value by key.
     *
     * @param key - The key by which the data will be deleted.
     */
    suspend fun <TYPE> removeProperty(key: Preferences.Key<TYPE>)

    /**
     * [Ru]
     * Метод для проверки, существуют ли данные по определённому ключу.
     *
     * @param key - ключ для проверки
     */

    /**
     * [En]
     * A method for checking if data exists for a specific key.
     *
     * @param key - key to check
     */
    fun <TYPE> isPropertyExist(key: Preferences.Key<TYPE>): Flow<Boolean>

    object Factory {
        fun createCommon(preferenceFile: File): PreferencesStorage {
            val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
                corruptionHandler = null,
                migrations = listOf(),
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
            ) { preferenceFile }
            return CommonPreferencesStorage(dataStore)
        }
    }
}