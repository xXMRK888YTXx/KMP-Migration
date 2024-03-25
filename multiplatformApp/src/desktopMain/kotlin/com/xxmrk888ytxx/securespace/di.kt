package com.xxmrk888ytxx.securespace

import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationUiModel
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.FinishConfigurationContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupCalculatorPasswordContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import com.xxmrk888ytxx.securespace.presentation.glue.FinishConfigurationContractImpl
import com.xxmrk888ytxx.securespace.presentation.glue.SetupCalculatorPasswordContractImpl
import com.xxmrk888ytxx.securespace.presentation.glue.SetupSecureSpacePasswordContractImpl
import com.xxmrk888ytxx.securespace.presentation.navigation.DesktopNavigatorController
import com.xxmrk888ytxx.securespace.presentation.navigation.DestinationFactory
import com.xxmrk888ytxx.securespace.presentation.navigation.DestinationFactoryImpl
import com.xxmrk888ytxx.securespace.presentation.navigation.WindowFactory
import com.xxmrk888ytxx.securespace.presentation.navigation.WindowFactoryImpl
import com.xxmrk888ytxx.securespace.presentation.navigation.WindowNavigatorController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue
import org.koin.dsl.module

val navigatorScopeQualifier = object : Qualifier {
    override val value: QualifierValue = "navigatorScopeQualifier"
}


val applicationModule = module {
    single<WindowNavigatorController> {
        DesktopNavigatorController(get(qualifier = navigatorScopeQualifier),get(),get())
    }
    single<WindowFactory> { WindowFactoryImpl() }
    single<DestinationFactory> { DestinationFactoryImpl() }
}

val firstConfigurationModule = module {
    factory { FirstConfigurationUiModel(get(),get(),get()) }
    factory<FinishConfigurationContract> { FinishConfigurationContractImpl() }
    factory<SetupCalculatorPasswordContract> { SetupCalculatorPasswordContractImpl() }
    factory<SetupSecureSpacePasswordContract> { SetupSecureSpacePasswordContractImpl() }
}

val scopeModule = module {
    single(qualifier = navigatorScopeQualifier) { CoroutineScope(Dispatchers.Default + SupervisorJob()) }
}