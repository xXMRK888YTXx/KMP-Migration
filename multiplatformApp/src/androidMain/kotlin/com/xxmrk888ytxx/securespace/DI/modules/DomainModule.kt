package com.xxmrk888ytxx.securespace.DI.modules

import android.content.Context
import com.xxmrk888ytxx.cryptomanager.CryptoManager
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import com.xxmrk888ytxx.securespace.DI.qualifiers.CheckCalculatorScope
import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import com.xxmrk888ytxx.securespace.data.CalculatorPasswordManagerImpl
import com.xxmrk888ytxx.securespace.data.FirstStartStateHolderImpl
import com.xxmrk888ytxx.securespace.data.OpenSecureScopeByCalculatorInputManagerImpl
import com.xxmrk888ytxx.securespace.data.SessionKeyHolderImpl
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder
import com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager
import com.xxmrk888ytxx.securespace.data.AndroidSecureSpaceManagerImpl
import com.xxmrk888ytxx.securespace.domain.SessionKeyHolder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
interface DomainModule {

    companion object {
        @Provides
        @AppScope
        fun bindOpenSecureScopeByCalculatorInputManager(
            calculatorInputManager: CalculatorPasswordManager,
            @CheckCalculatorScope checkCalculatorScope: CoroutineScope,
        ): OpenSecureScopeByCalculatorInputManager {
            return OpenSecureScopeByCalculatorInputManagerImpl(
                calculatorInputManager,
                checkCalculatorScope
            )
        }

        @Provides
        fun bindFirstStartStateHolder(
            preferencesStorage: PreferencesStorage,
        ): FirstStartStateHolder {
            return FirstStartStateHolderImpl(preferencesStorage)
        }

        @Provides
        fun bindCalculatorPasswordManager(
            preferencesStorage: PreferencesStorage,
            cryptoManager: CryptoManager,
        ): CalculatorPasswordManager {
            return CalculatorPasswordManagerImpl(preferencesStorage, cryptoManager)
        }

        @Provides
        @AppScope
        fun bindSessionKeyHolder(
        ): SessionKeyHolder {
            return SessionKeyHolderImpl()
        }

        @Provides
        fun bindSecureSpaceManager(
            context: Context,
        ): SecureSpaceManager {
            return AndroidSecureSpaceManagerImpl(context)
        }
    }
}