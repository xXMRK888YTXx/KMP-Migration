package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.passwordcryptomanager.PasswordCryptoManager
import dagger.Module
import dagger.Provides

@Module
class PasswordCryptoManagerModule {

    @Provides
    fun providePasswordCryptoManager() : PasswordCryptoManager {
        return PasswordCryptoManager.create()
    }
}