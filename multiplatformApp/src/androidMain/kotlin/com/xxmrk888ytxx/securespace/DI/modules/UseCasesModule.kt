package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.cryptomanager.CryptoManager
import com.xxmrk888ytxx.passwordcryptomanager.PasswordCryptoManager
import com.xxmrk888ytxx.securespace.data.CheckPasswordForLoginInSecureSpaceUseCaseImpl
import com.xxmrk888ytxx.securespace.data.useCase.CreateSecureSpaceUseCaseImpl
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager
import com.xxmrk888ytxx.securespace.domain.SessionKeyHolder
import com.xxmrk888ytxx.securespace.domain.useCase.CheckPasswordForLoginInSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.domain.useCase.CreateSecureSpaceUseCase
import dagger.Module
import dagger.Provides

@Module
interface UseCasesModule {
    companion object {
        @Provides
        fun bindCreateSecureSpaceUseCase(
            cryptoManager: CryptoManager,
            secureSpaceUseCase: SecureSpaceManager,
            passwordCryptoManager: PasswordCryptoManager,
        ): CreateSecureSpaceUseCase {
            return CreateSecureSpaceUseCaseImpl(
                cryptoManager,
                secureSpaceUseCase,
                passwordCryptoManager
            )
        }

        @Provides
        fun bindCheckPasswordForLoginInSecureSpaceUseCase(
            cryptoManager: CryptoManager,
            secureSpaceUseCase: SecureSpaceManager,
            passwordCryptoManager: PasswordCryptoManager,
            sessionKeyHolder: SessionKeyHolder,
        ): CheckPasswordForLoginInSecureSpaceUseCase {
            return CheckPasswordForLoginInSecureSpaceUseCaseImpl(
                secureSpaceUseCase,
                sessionKeyHolder,
                cryptoManager,
                passwordCryptoManager
            )
        }
    }
}