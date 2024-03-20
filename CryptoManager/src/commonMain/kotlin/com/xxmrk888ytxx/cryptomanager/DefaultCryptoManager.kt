package com.xxmrk888ytxx.cryptomanager

import java.security.MessageDigest

open class DefaultCryptoManager() : CryptoManager {
    override fun encryptData(bytes: ByteArray): ByteArray {
        return bytes.apply { reverse() }
    }

    override fun decryptData(encryptedData: ByteArray): ByteArray {
        return encryptedData.apply { reverse() }
    }

    override fun hashFromData(bytes: ByteArray): String {
        val digest: MessageDigest = MessageDigest.getInstance("SHA-512")
        val hashBytes: ByteArray = digest.digest(bytes)
        val hashString: StringBuilder = StringBuilder()

        for (aMessageDigest: Byte in hashBytes) {
            var h: String = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hashString.append(h)
        }

        return hashString.toString()
    }
}