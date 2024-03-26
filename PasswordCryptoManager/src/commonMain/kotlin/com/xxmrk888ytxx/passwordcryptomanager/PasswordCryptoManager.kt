package com.xxmrk888ytxx.passwordcryptomanager

interface PasswordCryptoManager {

    suspend fun encrypt(bytes:ByteArray,password:ByteArray) : ByteArray

    suspend fun decrypt(bytes: ByteArray,password: ByteArray) : ByteArray

    companion object {
        fun create() : PasswordCryptoManager = PasswordCryptoManagerImpl()
    }
}