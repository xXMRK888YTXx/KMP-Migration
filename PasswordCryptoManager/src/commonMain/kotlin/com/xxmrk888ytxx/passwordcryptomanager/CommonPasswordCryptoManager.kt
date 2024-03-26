package com.xxmrk888ytxx.passwordcryptomanager

internal class CommonPasswordCryptoManager : PasswordCryptoManager {
    override suspend fun encrypt(bytes: ByteArray, password: ByteArray): ByteArray {
        return bytes.apply { reverse() }
    }

    override suspend fun decrypt(bytes: ByteArray, password: ByteArray): ByteArray {
        return bytes.apply { reverse() }
    }
}