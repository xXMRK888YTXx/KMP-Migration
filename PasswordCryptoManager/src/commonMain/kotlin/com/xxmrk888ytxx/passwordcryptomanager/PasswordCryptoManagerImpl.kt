package com.xxmrk888ytxx.passwordcryptomanager

import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

internal class PasswordCryptoManagerImpl : PasswordCryptoManager {

    private val cryptMethod = "AES/GCM/NoPadding"

    private val ivSize = 32

    override suspend fun encrypt(bytes: ByteArray, password: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(cryptMethod)
        val keySpec: SecretKeySpec = SecretKeySpec(getHash(password),cryptMethod)

        val iv:ByteArray = SecureRandom().let {
            val array = ByteArray(ivSize)

            it.nextBytes(array)

            array
        }

        val ivSpec = IvParameterSpec(iv)

        cipher.init(Cipher.ENCRYPT_MODE, keySpec,ivSpec)


        return iv + cipher.doFinal(bytes)
    }

    override suspend fun decrypt(bytes: ByteArray, password: ByteArray): ByteArray {
        val iv = bytes.copyOf(ivSize)
        val encryptedBytes = bytes.drop(ivSize).toByteArray()

        val cipher = Cipher.getInstance(cryptMethod)
        val keySpec: SecretKeySpec = SecretKeySpec(getHash(password),cryptMethod)
        val ivSpec = IvParameterSpec(iv)

        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec)

        return cipher.doFinal(encryptedBytes)
    }


    private fun getHash(bytes: ByteArray) : ByteArray {
        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        val hashBytes: ByteArray = digest.digest(bytes)
        val hashString: StringBuilder = StringBuilder()

        for (aMessageDigest:Byte in hashBytes) {
            var h: String = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hashString.append(h)
        }

        return hashString.toString().take(32).encodeToByteArray()
    }


}