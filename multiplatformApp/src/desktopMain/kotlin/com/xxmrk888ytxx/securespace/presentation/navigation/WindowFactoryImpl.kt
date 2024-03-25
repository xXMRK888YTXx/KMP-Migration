package com.xxmrk888ytxx.securespace.presentation.navigation

import com.xxmrk888ytxx.securespace.presentation.navigation.window.CalculatorFlowWindow
import com.xxmrk888ytxx.securespace.presentation.navigation.window.SecureSpaceFlowWindow

class WindowFactoryImpl : WindowFactory {
    override suspend fun createWindow(
        windowId: Any,
        name: String,
        startDestination: Destination,
    ): WindowDestination {
        return when (windowId) {
            AppWindow.CalculatorFlow.id -> {
                CalculatorFlowWindow(startDestination, windowId, name)
            }

            AppWindow.SecureSpaceFlow.id -> {
                SecureSpaceFlowWindow(startDestination, windowId, name)
            }

            else -> error("Invalid windowId")
        }
    }
}