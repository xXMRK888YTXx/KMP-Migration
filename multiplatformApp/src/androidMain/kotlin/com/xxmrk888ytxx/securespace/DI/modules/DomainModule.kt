package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager.CalculatorPasswordManager
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager.CalculatorPasswordManagerImpl
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder.FirstStartStateHolder
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder.FirstStartStateHolderImpl
import com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager.OpenSecureScopeByCalculatorInputManager
import com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager.OpenSecureScopeByCalculatorInputManagerImpl
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager.SecureSpaceManager
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager.SecureSpaceManagerImpl
import com.xxmrk888ytxx.securespace.domain.SessionKeyHolder.SessionKeyHolder
import com.xxmrk888ytxx.securespace.domain.SessionKeyHolder.SessionKeyHolderImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    @AppScope
    fun bindOpenSecureScopeByCalculatorInputManager(
        openSecureScopeByCalculatorInputManagerImpl: OpenSecureScopeByCalculatorInputManagerImpl
    ) : OpenSecureScopeByCalculatorInputManager

    @Binds
    fun bindCalculatorPasswordManager(
        calculatorPasswordManagerImpl: CalculatorPasswordManagerImpl
    ) : CalculatorPasswordManager

    @Binds
    fun bindFirstStartStateHolder(
        firstStartStateHolderImpl: FirstStartStateHolderImpl
    ) : FirstStartStateHolder

    @Binds
    @AppScope
    fun bindSessionKeyHolder(
        sessionKeyHolderImpl: SessionKeyHolderImpl
    ) : SessionKeyHolder

    @Binds
    fun bindSecureSpaceManager(
        secureSpaceManagerImpl: SecureSpaceManagerImpl
    ) : SecureSpaceManager
}