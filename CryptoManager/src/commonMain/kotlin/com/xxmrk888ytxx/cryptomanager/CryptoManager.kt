package com.xxmrk888ytxx.cryptomanager


/**
 * [Ru]
 * Интерфейс шифрования данных.
 * Шифрование должно быть симитричным и использовать ключ который предоставляет AndroidKeyStore
 */
/**
 * [En]
 * Data encryption interface.
 * Encryption must be symmetric and use the key provided by AndroidKeyStore
 */
interface CryptoManager {

    fun encryptData(bytes: ByteArray): ByteArray

    fun decryptData(encryptedData: ByteArray): ByteArray

    fun hashFromData(bytes: ByteArray): String

    companion object {
        val multiplatformImplementation: CryptoManager
            get() = DefaultCryptoManager()
    }
}