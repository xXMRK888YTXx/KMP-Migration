package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.coreandroid.ApplicationScope
import com.xxmrk888ytxx.securespace.DI.qualifiers.ApplicationCoroutineScopeQualifier
import com.xxmrk888ytxx.securespace.DI.qualifiers.CheckCalculatorScope
import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
interface CoroutineScopeModule {

    companion object {
        @Provides
        @AppScope
        @ApplicationCoroutineScopeQualifier
        fun provideApplicationScope() : CoroutineScope {
            return ApplicationScope
        }

        @Provides
        @AppScope
        @CheckCalculatorScope
        fun provideCheckCalculatorScope() : CoroutineScope {
            return CoroutineScope(Dispatchers.IO + SupervisorJob())
        }
    }
}