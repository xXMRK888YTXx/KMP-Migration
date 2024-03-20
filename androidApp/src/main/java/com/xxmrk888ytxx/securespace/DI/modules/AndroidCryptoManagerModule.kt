package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.cryptomanager.AndroidCryptoManager
import dagger.Module
import dagger.Provides

@Module
class AndroidCryptoManagerModule {

    @Provides
    fun provideAndroidCryptoManager() : AndroidCryptoManager {
        return AndroidCryptoManager.create()
    }
}