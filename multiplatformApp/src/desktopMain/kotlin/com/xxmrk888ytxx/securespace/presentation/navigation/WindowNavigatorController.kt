package com.xxmrk888ytxx.securespace.presentation.navigation

import kotlinx.coroutines.flow.StateFlow

interface WindowNavigatorController {
    val currentWindows: StateFlow<List<WindowDestination>>
    fun addWindow(
        windowId: Any,
        startDestinationId: Any,
        windowName: String,
    )

    fun removeWindow(windowId: Any)
    fun addDestination(windowId: Any, destinationId: Any)
    fun removeDestination(windowId: Any, destinationId: Any)
    fun popDestinationFromWindow(windowId: Any)
}