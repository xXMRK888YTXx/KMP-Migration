package com.xxmrk888ytxx.securespace

import com.xxmrk888ytxx.securespace.domain.FirstStartStateHolder
import com.xxmrk888ytxx.securespace.presentation.navigation.AppWindow
import com.xxmrk888ytxx.securespace.presentation.navigation.WindowNavigatorController
import com.xxmrk888ytxx.securespace.presentation.navigation.destination.CalculatorDestination
import com.xxmrk888ytxx.securespace.presentation.navigation.destination.FirstConfigurationDestination
import com.xxmrk888ytxx.shared.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

object DesktopApplication : Navigator {

    val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    val windowNavigator by inject<WindowNavigatorController>(WindowNavigatorController::class.java)


    suspend fun onApplicationStarted() {
        startKoin {
            modules(applicationModule, scopeModule, firstConfigurationModule, domainModule)
        }

        val firstStartStateHolder by inject<FirstStartStateHolder>(FirstStartStateHolder::class.java)

        if(firstStartStateHolder.isFirstStartOfApplication.first()) {
            windowNavigator.addWindow(
                AppWindow.CalculatorFlow.id,
                FirstConfigurationDestination.id,
                ""
            )
        } else {
            windowNavigator.addWindow(
                AppWindow.CalculatorFlow.id,
                CalculatorDestination.id,
                ""
            )
        }
    }

    override fun toLoginInSecureSpaceScreen() {

    }

    override fun toCalculatorScreen() {
        windowNavigator.addDestination(AppWindow.CalculatorFlow.id,CalculatorDestination.id)
        windowNavigator.removeDestination(AppWindow.CalculatorFlow.id,FirstConfigurationDestination.id)
    }

    override fun toSecureSpaceMainScreen() {
    }
}