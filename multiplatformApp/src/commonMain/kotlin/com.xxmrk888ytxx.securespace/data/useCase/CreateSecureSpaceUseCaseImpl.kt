package com.xxmrk888ytxx.securespace.data.useCase

import com.xxmrk888ytxx.cryptomanager.CryptoManager
import com.xxmrk888ytxx.passwordcryptomanager.PasswordCryptoManager
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager
import com.xxmrk888ytxx.securespace.domain.useCase.CreateSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.domain.useCase.exceptions.SecureSpaceAlreadyCreatedException
import com.xxmrk888ytxx.securespace.expected.ioDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileOutputStream

class CreateSecureSpaceUseCaseImpl(
    private val cryptoManager: CryptoManager,
    private val secureSpaceManager: SecureSpaceManager,
    private val passwordCryptoManager: PasswordCryptoManager
) : CreateSecureSpaceUseCase {
    override suspend fun execute(passwordOfSecureSpace: String) = withContext(ioDispatcher) {
        val passwordHash = cryptoManager.hashFromData(passwordOfSecureSpace.encodeToByteArray()).encodeToByteArray()

        secureSpaceManager.secureSpaceDir.mkdir()

        if(secureSpaceManager.checkFile.exists()) throw SecureSpaceAlreadyCreatedException()

        FileOutputStream(secureSpaceManager.checkFile).use {
            it.write(passwordCryptoManager.encrypt(SecureSpaceManager.CHECK_VERIFICATION_KEY,passwordHash))
        }

    }
}