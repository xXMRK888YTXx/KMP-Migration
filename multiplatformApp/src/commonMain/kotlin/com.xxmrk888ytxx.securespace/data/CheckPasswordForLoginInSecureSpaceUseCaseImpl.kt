package com.xxmrk888ytxx.securespace.data

import com.xxmrk888ytxx.cryptomanager.CryptoManager
import com.xxmrk888ytxx.passwordcryptomanager.PasswordCryptoManager
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager
import com.xxmrk888ytxx.securespace.domain.SessionKeyHolder
import com.xxmrk888ytxx.securespace.domain.models.SessionCryptoKey
import com.xxmrk888ytxx.securespace.domain.useCase.CheckPasswordForLoginInSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.domain.useCase.exceptions.CheckVerificationNotMatchExpected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import javax.crypto.BadPaddingException

class CheckPasswordForLoginInSecureSpaceUseCaseImpl constructor(
    private val secureSpaceManager: SecureSpaceManager,
    private val sessionKeyHolder: SessionKeyHolder,
    private val androidCryptoManager: CryptoManager,
    private val passwordCryptoManager: PasswordCryptoManager
) : CheckPasswordForLoginInSecureSpaceUseCase {

    override suspend fun execute(password: String): Boolean = withContext(Dispatchers.IO) {
        val passwordHash = androidCryptoManager.hashFromData(password.toByteArray(Charsets.UTF_8)).toByteArray(
            Charsets.UTF_8
        )

        val dataForCheckSecureSpacePassword = FileInputStream(secureSpaceManager.checkFile).let {
            val data = it.readBytes()

            it.close()

            data
        }

        return@withContext try {

            if(!passwordCryptoManager.decrypt(dataForCheckSecureSpacePassword,passwordHash).contentEquals(
                    SecureSpaceManager.CHECK_VERIFICATION_KEY)) throw CheckVerificationNotMatchExpected()

            sessionKeyHolder.setupKey(SessionCryptoKey(passwordHash))
            true
        }catch (e:BadPaddingException) {
            false
        } catch (e: CheckVerificationNotMatchExpected) {
            false
        }
    }
}