package com.xxmrk888ytxx.securespace

import com.xxmrk888ytxx.securespace.presentation.navigation.AppWindow
import com.xxmrk888ytxx.securespace.presentation.navigation.WindowNavigatorController
import com.xxmrk888ytxx.securespace.presentation.navigation.destination.FirstConfigurationDestination
import com.xxmrk888ytxx.shared.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.java.KoinJavaComponent.inject

object DesktopApplication : Navigator {

    val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    val windowNavigator by inject<WindowNavigatorController>(WindowNavigatorController::class.java)


    fun onApplicationPrepared() {
        windowNavigator.addWindow(
            AppWindow.CalculatorFlow.id,
            FirstConfigurationDestination.id,
            "Onboarding"
        )
    }

    override fun toLoginInSecureSpaceScreen() {

    }

    override fun toCalculatorScreen() {
    }

    override fun toSecureSpaceMainScreen() {
    }
}