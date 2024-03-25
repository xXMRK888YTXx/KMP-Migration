package com.xxmrk888ytxx.securespace.DI

import android.content.Context
import com.xxmrk888ytxx.securespace.DI.modules.AndroidCoreModule
import com.xxmrk888ytxx.securespace.DI.modules.AndroidCryptoManagerModule
import com.xxmrk888ytxx.securespace.DI.modules.CalculatorScreenModule
import com.xxmrk888ytxx.securespace.DI.modules.CoroutineScopeModule
import com.xxmrk888ytxx.securespace.DI.modules.DomainModule
import com.xxmrk888ytxx.securespace.DI.modules.FirstConfigurationScreenModule
import com.xxmrk888ytxx.securespace.DI.modules.LoginInSecureSpaceScreenModule
import com.xxmrk888ytxx.securespace.DI.modules.MainSecureSpaceModule
import com.xxmrk888ytxx.securespace.DI.modules.PasswordCryptoManagerModule
import com.xxmrk888ytxx.securespace.DI.modules.PreferencesStorageModule
import com.xxmrk888ytxx.securespace.DI.modules.UseCasesModule
import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import com.xxmrk888ytxx.securespace.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AndroidCoreModule::class,
        CoroutineScopeModule::class,
        CalculatorScreenModule::class,
        DomainModule::class,
        FirstConfigurationScreenModule::class,
        PreferencesStorageModule::class,
        AndroidCryptoManagerModule::class,
        PasswordCryptoManagerModule::class,
        UseCasesModule::class,
        LoginInSecureSpaceScreenModule::class,
        MainSecureSpaceModule::class,
    ]
)
@AppScope
internal interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }
}