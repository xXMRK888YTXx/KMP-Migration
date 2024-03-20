package com.xxmrk888ytxx.preferencesstorage

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.nio.charset.StandardCharsets.UTF_8

@RunWith(AndroidJUnit4::class)
class EncryptedPreferencesStorageTest {

    object PreferencesStorageHolder {
        private val context by lazy { InstrumentationRegistry.getInstrumentation().targetContext}
        val preferencesStorage: PreferencesStorage = PreferencesStorage.Factory().create("test", context)
    }

    private val testKey = stringPreferencesKey("test")

    private val cipher = object : Cipher {
        override fun encrypt(byteArray: ByteArray): ByteArray {
            return byteArray.apply { reverse() }
        }

        override fun decrypt(byteArray: ByteArray): ByteArray {
            return byteArray.apply { reverse() }
        }

    }

    private val encryptedPreferencesStorage = EncryptedPreferencesStorage.create(
        PreferencesStorageHolder.preferencesStorage,
        cipher
    )

    @Before
    fun init() = runBlocking {
        encryptedPreferencesStorage.removeProperty(testKey)
    }

    @Test
    fun callPreferencesStorageMethodsExpectThisWorkCorrent() = runBlocking {
        Assert.assertEquals(null,encryptedPreferencesStorage.getPropertyOrNull(testKey).first())
        val testString = "test"

        encryptedPreferencesStorage.writeProperty(testKey,testString.toByteArray())

        Assert.assertEquals(true,encryptedPreferencesStorage.isPropertyExist(testKey).first())

        Assert.assertEquals(testString,encryptedPreferencesStorage.getPropertyOrNull(testKey).first()?.toString(UTF_8))

        encryptedPreferencesStorage.removeProperty(testKey)

        Assert.assertEquals(false,encryptedPreferencesStorage.isPropertyExist(testKey).first())

    }
}