package com.xxmrk888ytxx.passwordcryptomanager

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import javax.crypto.BadPaddingException
import kotlin.random.Random
import kotlin.text.Charsets.UTF_8

@RunWith(AndroidJUnit4::class)
class PasswordCryptoManagerImplTest {

    private val passwordCryptoManager:PasswordCryptoManager = PasswordCryptoManagerImpl()







    @Test
    fun testEncryptIfStringsNotEqualsAndPasswordNotEqualsMethodExpectEncryptNotEqualsStrings() = runBlocking {
        val str1 = "test"
        val str2 = "test2"
        val pas = getTestPassword()
        val pas2 = getTestPassword("testPassword2")


        val eStr = passwordCryptoManager.encrypt(str1.toByteArray(UTF_8),pas)
        val eStr2 = passwordCryptoManager.encrypt(str2.toByteArray(UTF_8),pas2)

        Assert.assertNotEquals(eStr,eStr2)
    }

    @Test
    fun testEncryptIfStringsEqualsAndPasswordNotEqualsMethodExpectEncryptNotEqualsStrings() = runBlocking {
        val str1 = "test"
        val str2 = "test"
        val pas = getTestPassword()
        val pas2 = getTestPassword("testPassword2")

        val eStr = passwordCryptoManager.encrypt(str1.toByteArray(UTF_8),pas)
        val eStr2 = passwordCryptoManager.encrypt(str2.toByteArray(UTF_8),pas2)

        Assert.assertNotEquals(eStr,eStr2)
    }

    @Test
    fun testEncryptIfStringsNotEqualsAndPasswordEqualsMethodExpectEncryptNotEqualsStrings() = runBlocking {
        val str1 = "test"
        val str2 = "test2"
        val pas = getTestPassword()
        val pas2 = getTestPassword()

        val eStr = passwordCryptoManager.encrypt(str1.toByteArray(UTF_8),pas)
        val eStr2 = passwordCryptoManager.encrypt(str2.toByteArray(UTF_8),pas2)

        Assert.assertNotEquals(eStr,eStr2)
    }

    @Test
    fun testEncryptIfStringsEqualsAndPasswordEqualsMethodExpectEncryptEqualsStrings() = runBlocking {
        val str1 = "test"
        val str2 = "test"
        val pas = getTestPassword()
        val pas2 = getTestPassword()

        val eStr = passwordCryptoManager.encrypt(str1.toByteArray(UTF_8),pas)
        val eStr2 = passwordCryptoManager.encrypt(str2.toByteArray(UTF_8),pas2)

        val dStr = passwordCryptoManager.decrypt(eStr,pas)
        val dStr2 = passwordCryptoManager.decrypt(eStr2,pas)

        Assert.assertEquals(dStr.toString(UTF_8),dStr2.toString(UTF_8))
        Assert.assertNotEquals(eStr.toString(UTF_8),eStr2.toString(UTF_8))
    }

    @Test
    fun testDecrepitImputeStringExpectPrimaryString() = runBlocking {
        val str = "Mozart X".toByteArray(UTF_8)
        val password = getTestPassword()

        val eStr = passwordCryptoManager.encrypt(str,password)
        val dStr = passwordCryptoManager.decrypt(eStr,password)

        Assert.assertEquals(str.toString(UTF_8),dStr.toString(UTF_8))
    }

    @Test(expected = BadPaddingException::class)
    fun testDecryptInputStringIfPasswordIValidExpectException() = runBlocking {
        val str = "Mozart X"
        val password = getTestPassword()
        val password2 = getTestPassword("testPassword1")

        val eStr = passwordCryptoManager.encrypt(str.toByteArray(UTF_8),password)
        val dStr = passwordCryptoManager.decrypt(eStr,password2)
    }


    @Test
    fun testDecrepitIfPasswordVeryLongExpectDecryptString() = runBlocking {
        val str = "test".toByteArray(UTF_8)
        val pass = getTestPassword(Random(System.currentTimeMillis()).nextBytes(1000).toString())

        val eString = passwordCryptoManager.encrypt(str,pass)
        val dString = passwordCryptoManager.decrypt(eString,pass)

        Assert.assertEquals(str.toString(UTF_8),dString.toString(UTF_8))
    }

    @Test
    fun testDecrepitIfTextVeryLongExpectDecryptString() = runBlocking {
        val str =  "425678ythgfddfofxgdhubhddhfgh7udfgdfghdfghfdgdfgdfgfd8gd9dfughdfgdf8ughdfg8udfgd89g8dfhgfdgfhdugdfghdfug".toByteArray(
            UTF_8)
        val pass = getTestPassword()

        val eString = passwordCryptoManager.encrypt(str,pass)
        val dString = passwordCryptoManager.decrypt(eString,pass)

        Assert.assertEquals(str.toString(UTF_8),dString.toString(UTF_8))
    }

    private fun getTestPassword(password:String = "testPassword78555855689856244526") = password.toByteArray(UTF_8)
}