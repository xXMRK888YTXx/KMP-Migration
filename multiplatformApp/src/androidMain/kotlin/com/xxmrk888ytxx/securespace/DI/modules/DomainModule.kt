package com.xxmrk888ytxx.securespace.DI.modules

import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager
import com.xxmrk888ytxx.securespace.data.CalculatorPasswordManagerImpl
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder
import com.xxmrk888ytxx.securespace.data.FirstStartStateHolderImpl
import com.xxmrk888ytxx.securespace.domain.OpenSecureScopeByCalculatorInputManager
import com.xxmrk888ytxx.securespace.data.OpenSecureScopeByCalculatorInputManagerImpl
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager.AndroidSecureSpaceManagerImpl
import com.xxmrk888ytxx.securespace.domain.SessionKeyHolder
import com.xxmrk888ytxx.securespace.data.SessionKeyHolderImpl
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
        androidSecureSpaceManagerImpl: AndroidSecureSpaceManagerImpl
    ) : SecureSpaceManager
}