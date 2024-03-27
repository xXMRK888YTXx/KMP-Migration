package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.passwordcryptomanager.PasswordCryptoManager
import com.xxmrk888ytxx.passwordcryptomanager.createAndroid
import dagger.Module
import dagger.Provides

@Module
class PasswordCryptoManagerModule {

    @Provides
    fun providePasswordCryptoManager() : PasswordCryptoManager {
        return PasswordCryptoManager.Factory.createAndroid()
    }
}