package com.xxmrk888ytxx.passwordcryptomanager

interface PasswordCryptoManager {

    suspend fun encrypt(bytes:ByteArray,password:ByteArray) : ByteArray

    suspend fun decrypt(bytes: ByteArray,password: ByteArray) : ByteArray

    object Factory {
        fun createCommon() : PasswordCryptoManager = CommonPasswordCryptoManager()
    }
}