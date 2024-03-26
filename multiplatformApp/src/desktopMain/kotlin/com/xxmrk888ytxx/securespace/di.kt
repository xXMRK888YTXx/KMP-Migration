package com.xxmrk888ytxx.securespace

import com.xxmrk888ytxx.cryptomanager.CryptoManager
import com.xxmrk888ytxx.firstconfigurationscreen.FirstConfigurationUiModel
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.FinishConfigurationContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupCalculatorPasswordContract
import com.xxmrk888ytxx.firstconfigurationscreen.contracts.SetupSecureSpacePasswordContract
import com.xxmrk888ytxx.passwordcryptomanager.PasswordCryptoManager
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import com.xxmrk888ytxx.securespace.data.CalculatorPasswordManagerImpl
import com.xxmrk888ytxx.securespace.data.DesktopSecureSpaceManager
import com.xxmrk888ytxx.securespace.data.FirstStartStateHolderImpl
import com.xxmrk888ytxx.securespace.data.useCase.CreateSecureSpaceUseCaseImpl
import com.xxmrk888ytxx.securespace.domain.CalculatorPasswordManager
import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder
import com.xxmrk888ytxx.securespace.domain.SecureSpaceManager
import com.xxmrk888ytxx.securespace.domain.useCase.CreateSecureSpaceUseCase
import com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen.FinishConfigurationContractImpl
import com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen.SetupCalculatorPasswordContractImpl
import com.xxmrk888ytxx.securespace.glue.firstConfigurationScreen.SetupSecureSpacePasswordContractImpl
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
import java.io.File


val navigatorScopeQualifier = object : Qualifier {
    override val value: QualifierValue = "navigatorScopeQualifier"
}


val applicationModule = module {
    single<WindowNavigatorController> {
        DesktopNavigatorController(get(qualifier = navigatorScopeQualifier),get(),get())
    }
    single<WindowFactory> { WindowFactoryImpl() }
    single<DestinationFactory> { DestinationFactoryImpl() }
    //App files dir
    single<File> {
        File("data").apply {
            mkdir()
        }
    }
    factory { DesktopSecureSpaceManager(get()) }
}

val domainModule = module {
    single<FirstStartStateHolder> { FirstStartStateHolderImpl(get()) }
    single<CalculatorPasswordManager> { CalculatorPasswordManagerImpl(get(),get()) }
    single<CryptoManager> { CryptoManager.multiplatformImplementation }
    single<PreferencesStorage> { PreferencesStorage.Factory.createCommon(File("data/PreferencesStorage.preferences_pb")) }
    factory<PasswordCryptoManager> { PasswordCryptoManager.Factory.createCommon() }
    factory<CreateSecureSpaceUseCase> { CreateSecureSpaceUseCaseImpl(get(),get(),get()) }
    single<SecureSpaceManager> { DesktopSecureSpaceManager(get()) }
}

val firstConfigurationModule = module {
    factory { FirstConfigurationUiModel(get(),get(),get()) }
    factory<FinishConfigurationContract> { FinishConfigurationContractImpl(get()) }
    factory<SetupCalculatorPasswordContract> { SetupCalculatorPasswordContractImpl(get()) }
    factory<SetupSecureSpacePasswordContract> { SetupSecureSpacePasswordContractImpl(get()) }
}

val scopeModule = module {
    single(qualifier = navigatorScopeQualifier) { CoroutineScope(Dispatchers.Default + SupervisorJob()) }
}