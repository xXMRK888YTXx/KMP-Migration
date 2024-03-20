package com.xxmrk888ytxx.securespace.DI.modules

import android.content.Context
import com.xxmrk888ytxx.coreandroid.AndroidLogger
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Logger
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.ToastManager
import com.xxmrk888ytxx.coreandroid.ToastManagerImpl
import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
interface AndroidCoreModule {

    companion object {

        @Provides
        fun provideToastManager(context: Context) : ToastManager {
            return ToastManagerImpl(context)
        }

        @Provides
        @AppScope
        fun provideLogger() : Logger {
            return AndroidLogger
        }
    }
}