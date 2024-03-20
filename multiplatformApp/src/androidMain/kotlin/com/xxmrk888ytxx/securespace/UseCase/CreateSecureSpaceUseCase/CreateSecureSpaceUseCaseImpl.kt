package com.xxmrk888ytxx.securespace.UseCase.CreateSecureSpaceUseCase

import com.xxmrk888ytxx.cryptomanager.CryptoManager
import com.xxmrk888ytxx.passwordcryptomanager.PasswordCryptoManager
import com.xxmrk888ytxx.securespace.UseCase.CreateSecureSpaceUseCase.exceptions.SecureSpaceAlreadyCreatedException
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager.SecureSpaceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.text.Charsets.UTF_8

class CreateSecureSpaceUseCaseImpl @Inject constructor(
    private val androidCryptoManager: CryptoManager,
    private val secureSpaceManager: SecureSpaceManager,
    private val passwordCryptoManager: PasswordCryptoManager
) : CreateSecureSpaceUseCase {



    override suspend fun execute(passwordOfSecureSpace: String) = withContext(Dispatchers.IO) {
        val passwordHash = androidCryptoManager.hashFromData(passwordOfSecureSpace.toByteArray(UTF_8)).toByteArray(UTF_8)

        secureSpaceManager.secureSpaceDir.mkdir()

        if(secureSpaceManager.checkFile.exists()) throw SecureSpaceAlreadyCreatedException()

        FileOutputStream(secureSpaceManager.checkFile).use {
            it.write(passwordCryptoManager.encrypt(SecureSpaceManager.CHECK_VERIFICATION_KEY,passwordHash))
        }

    }
}