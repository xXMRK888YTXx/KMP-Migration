package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.cryptomanager.CryptoManager
import dagger.Module
import dagger.Provides
import com.xxmrk888ytxx.cryptomanager.androidImplementation

@Module
class AndroidCryptoManagerModule {
    @Provides
    fun provideAndroidCryptoManager() : CryptoManager {
        return CryptoManager.androidImplementation
    }
}